package com.example.thesis_todoapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.thesis_todoapp.data.TodoItem
import com.example.thesis_todoapp.ui.components.TodoTaskItemList
import com.example.thesis_todoapp.ui.theme.ThesisTodoAppTheme
import java.util.*

@Composable
fun TodoListScreen(){
    Column(modifier = Modifier
        .fillMaxWidth(1f)
        .fillMaxHeight(1f)
        .background(Color(0xFFF2F2F7))
    ) {
        IconButton(onClick = { /*TODO*/ }, content = { Icon(Icons.Filled.Add, "") }, modifier = Modifier.align(Alignment.End))
        Text(
            text = "Todo List",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )

        val list = remember{ List(70) { TodoItem(it, "Hello " + it, Date()) }.toMutableStateList() }

        TodoTaskItemList(
            list = list,
            onCloseTask = { todo -> list.remove(todo) }
        )
    }
}

@Preview
@Composable
fun PreviewMainScreen(){
    ThesisTodoAppTheme {
        TodoListScreen()
    }
}