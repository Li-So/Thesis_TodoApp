package com.example.thesis_todoapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thesis_todoapp.data.TodoItem
import com.example.thesis_todoapp.ui.components.SheetAddTodo
import com.example.thesis_todoapp.ui.components.TodoTaskItemList
import com.example.thesis_todoapp.ui.theme.ThesisTodoAppTheme
import com.example.thesis_todoapp.viewmodels.TodoListViewModel
import kotlinx.coroutines.launch
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(todoListViewModel: TodoListViewModel){
    var todoLabel by rememberSaveable{ mutableStateOf("")}
    val coroutineScope = rememberCoroutineScope()
    val sortedTodoList by todoListViewModel.sortedTodoTasks.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth(1f)
        .fillMaxHeight(1f)
        .background(Color(0xFFF2F2F7))
        .padding(15.dp)
    ) {


        //val list = remember{ List(70) { TodoItem(it, "Hello " + it, Date()) }.toMutableStateList() }
        /*TODO*/
        SheetAddTodo {
            Column {
                Text("Task")
                TextField(
                    value = todoLabel,
                    onValueChange = { todoLabel = it },
                    placeholder = { Text("Task name") }
                )
                OutlinedButton(
                    onClick = {
                        if(todoLabel != ""){
                            coroutineScope.launch {
                                todoListViewModel.saveTodoItem(
                                    TodoItem(
                                        label = todoLabel,
                                        dateChecked = Date()
                                    )
                                )
                            }

                        }
                    }
                ) {
                    Text("Add")
                }
            }
        }

        Text(
            text = "Todo List",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth()
        )

        TodoTaskItemList(
            list = sortedTodoList.todoList,
            onCloseTask = { todo ->
                coroutineScope.launch {
                    todoListViewModel.deleteTodoItem(todo)
                }
            },
            onCheckedChange = { todo ->
                coroutineScope.launch {
                    todo.isChecked = !todo.isChecked
                    todoListViewModel.updateTodoItem(todo)
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewMainScreen(){
    ThesisTodoAppTheme {
        TodoListScreen(todoListViewModel = viewModel())
    }
}