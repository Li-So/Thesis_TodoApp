package com.example.thesis_todoapp.viewmodels

import android.util.Log
import androidx.compose.runtime.currentRecomposeScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesis_todoapp.data.TodoItem
import com.example.thesis_todoapp.data.TodoItemsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.minutes

class TodoListViewModel(private val todoItemsRepository: TodoItemsRepository): ViewModel() {
    //val list = remember{ List(70) { TodoItem(it, "Hello " + it, Date()) }.toMutableStateList() }
    val todoTasks: StateFlow<TodoItemList> = todoItemsRepository.getAllTodoItemsStream().map { TodoItemList(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = TodoItemList()
    )



    val sortedTodoTasks: StateFlow<TodoItemList> = todoItemsRepository.getSortedTodoItemsStream().map { TodoItemList(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = TodoItemList()
    )


    init {

        viewModelScope.launch {
                Log.d("DEBUG","Viewing Delete: ${sortedTodoTasks.value.todoList.size} ")
                sortedTodoTasks.collect{
                        for (todos in it.todoList){
                            Log.d("DEBUG","Attempting Delete")
                            deleteOldTodoItem(todos)
                            if(todos.id == it.todoList.last().id){
                                cancel("Runs only once")
                            }
                        }


                }

        }
    }

    suspend fun updateTodoItem(todoItem: TodoItem){
        todoItemsRepository.updateTodoItem(todoItem)
    }

    suspend fun deleteTodoItem(todoItem: TodoItem){
        todoItemsRepository.deleteTodoItem(todoItem)
    }

    suspend fun saveTodoItem(todoItem: TodoItem) {
        todoItemsRepository.insertTodoItem(todoItem)
    }

    private suspend fun deleteOldTodoItem(todoItem: TodoItem){
        if(todoItem.dateChecked.time.days < Date().time.days && todoItem.isChecked){
            coroutineScope {
                deleteTodoItem(todoItem = todoItem)
            }
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class TodoItemList(val todoList: List<TodoItem> = listOf())