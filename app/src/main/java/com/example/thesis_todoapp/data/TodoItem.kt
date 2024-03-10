package com.example.thesis_todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todo_items")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val label: String,
    val isChecked: Boolean = false,
    val dateChecked: Date
)