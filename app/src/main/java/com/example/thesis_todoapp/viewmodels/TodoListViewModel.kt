package com.example.thesis_todoapp.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.thesis_todoapp.data.TodoItem

class TodoListViewModel: ViewModel() {
    private val _todoTasks = emptyList<TodoItem>().toMutableStateList()
    val tasks: List<TodoItem>
        get() = _todoTasks

    fun remove(taskItem: TodoItem){
        _todoTasks.remove(taskItem)
    }

    fun add(taskItem: TodoItem){
        _todoTasks.add(taskItem)
    }
}