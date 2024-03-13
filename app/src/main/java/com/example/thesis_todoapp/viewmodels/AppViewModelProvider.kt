package com.example.thesis_todoapp.viewmodels

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.thesis_todoapp.TodoApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {

        initializer {
            TodoListViewModel(todoApplication().container.todoItemsRepository)
        }
    }
}

fun CreationExtras.todoApplication(): TodoApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)