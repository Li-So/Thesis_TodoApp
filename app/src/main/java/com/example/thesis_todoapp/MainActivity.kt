package com.example.thesis_todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thesis_todoapp.ui.screens.TodoListScreen
import com.example.thesis_todoapp.ui.theme.ThesisTodoAppTheme
import com.example.thesis_todoapp.viewmodels.AppViewModelProvider
import com.example.thesis_todoapp.viewmodels.TodoListViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThesisTodoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}

@Composable
fun AppScreen(todoListViewModel: TodoListViewModel = viewModel(factory = AppViewModelProvider.Factory) ){
    Row{
        TodoListScreen(todoListViewModel)
    }

}