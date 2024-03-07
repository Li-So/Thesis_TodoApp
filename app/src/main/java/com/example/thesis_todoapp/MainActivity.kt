package com.example.thesis_todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.thesis_todoapp.ui.screens.TodoListScreen
import com.example.thesis_todoapp.ui.theme.ThesisTodoAppTheme
import com.example.thesis_todoapp.viewmodels.TodoListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThesisTodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}

@Composable
fun AppScreen(todoListViewModel: TodoListViewModel = viewModel() ){
    TodoListScreen(todoListViewModel)
}