package com.example.thesis_todoapp.data

import android.content.Context

interface AppContainer {
    val todoItemsRepository: TodoItemsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val todoItemsRepository: TodoItemsRepository by lazy {
        OfflineItemsRepository(TodoDatabase.getDatabase(context).todoItemDao())
    }
}

