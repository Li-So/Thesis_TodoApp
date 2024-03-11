package com.example.thesis_todoapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: TodoItem)

    @Update
    suspend fun update(todo: TodoItem)

    @Delete
    suspend fun delete(todo: TodoItem)

    @Query("SELECT * FROM todo_items WHERE id = :id")
    fun getTodoItem(id: Int): Flow<TodoItem>

    @Query("SELECT * from todo_items ORDER BY label ASC")
    fun getAllTodoItems(): Flow<List<TodoItem>>

    @Query("SELECT * FROM todo_items ORDER BY isChecked ASC")
    fun getSortedTodoItems(): Flow<List<TodoItem>>
}