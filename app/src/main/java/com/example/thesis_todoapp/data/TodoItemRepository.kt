package com.example.thesis_todoapp.data

import kotlinx.coroutines.flow.Flow

interface TodoItemsRepository {
    fun getAllTodoItemsStream(): Flow<List<TodoItem>>

    fun getTodoItemStream(id: Int): Flow<TodoItem?>

    suspend fun insertTodoItem(todoItem: TodoItem)

    suspend fun deleteTodoItem(todoItem: TodoItem)
}