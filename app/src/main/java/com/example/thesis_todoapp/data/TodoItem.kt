package com.example.thesis_todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

@Entity(tableName = "todoItems")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    val dateChecked: Date
)