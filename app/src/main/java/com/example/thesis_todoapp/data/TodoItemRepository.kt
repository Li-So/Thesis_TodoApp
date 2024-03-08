package com.example.thesis_todoapp.data

import android.content.ClipData.Item
import kotlinx.coroutines.flow.Flow

interface TodoItemsRepository {
    fun getAllTodoItemsStream(): Flow<List<TodoItem>>

    fun getItemStream(id: Int): Flow<TodoItem?>

    suspend fun insertTodoItem(todoItem: TodoItem)

    suspend fun deleteTodoItem(item: Item)
}