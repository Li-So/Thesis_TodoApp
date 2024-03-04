package com.example.thesis_todoapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thesis_todoapp.data.TodoItem
import com.example.thesis_todoapp.ui.theme.ThesisTodoAppTheme
import java.util.*

@Composable
fun TodoTaskItemList(
    modifier: Modifier = Modifier,
    list: List<TodoItem>
){
    LazyColumn(
        modifier = modifier
    ){
        items(items = list, key = {todoTask -> todoTask.id}){
            TodoTaskItem(taskName = it.label)
        }
    }
}

@Preview
@Composable
fun TodoTaskItemListPreview(){
    ThesisTodoAppTheme {
        TodoTaskItemList(Modifier, List(3) { TodoItem(it,"Hello", Date() ) } )
    }
}