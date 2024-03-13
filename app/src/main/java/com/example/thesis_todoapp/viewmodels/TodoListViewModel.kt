package com.example.thesis_todoapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesis_todoapp.data.TodoItem
import com.example.thesis_todoapp.data.TodoItemsRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import kotlin.time.Duration.Companion.days

class TodoListViewModel(private val todoItemsRepository: TodoItemsRepository): ViewModel() {
    val sortedTodoItemList: StateFlow<List<TodoItem>> = todoItemsRepository.getSortedTodoItemsStream().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = listOf()
    )
    init {
        viewModelScope.launch {
                sortedTodoItemList.collect{ todoList ->
                        for (todos in todoList){
                            deleteOldCheckedTodoItem(todos)
                            if(todos.id == todoList.last().id){
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

    private suspend fun deleteOldCheckedTodoItem(todoItem: TodoItem){
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