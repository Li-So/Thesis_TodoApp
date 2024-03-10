package com.example.thesis_todoapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao

    companion object {
        @Volatile
        private var Instance: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return Instance?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    TodoDatabase::class.java,
                    "todo_item_database"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}