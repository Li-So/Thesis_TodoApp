package com.example.thesis_todoapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesis_todoapp.data.TodoItem
import com.example.thesis_todoapp.data.TodoItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TodoListViewModel(private val todoItemsRepository: TodoItemsRepository): ViewModel() {
    //val list = remember{ List(70) { TodoItem(it, "Hello " + it, Date()) }.toMutableStateList() }
    val todoTasks: StateFlow<TodoItemList> = todoItemsRepository.getAllTodoItemsStream().map { TodoItemList(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        initialValue = TodoItemList()
    )

    suspend fun deleteTodoItem(taskItem: TodoItem){
        todoItemsRepository.deleteTodoItem(taskItem)
    }

    suspend fun saveTodoItem(todoItem: TodoItem) {
        todoItemsRepository.insertTodoItem(todoItem)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class TodoItemList(val todoList: List<TodoItem> = listOf())