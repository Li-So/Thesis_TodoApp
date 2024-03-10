package com.example.thesis_todoapp.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val todoItemDao: TodoItemDao): TodoItemsRepository {
    override fun getAllTodoItemsStream(): Flow<List<TodoItem>> = todoItemDao.getAllTodoItems()

    override fun getTodoItemStream(id: Int): Flow<TodoItem?> = todoItemDao.getTodoItem(id)

    override suspend fun insertTodoItem(todoItem: TodoItem) = todoItemDao.insert(todoItem)

    override suspend fun updateTodoItem(todoItem: TodoItem) = todoItemDao.update(todoItem)

    override suspend fun deleteTodoItem(todoItem: TodoItem) = todoItemDao.delete(todoItem)
}