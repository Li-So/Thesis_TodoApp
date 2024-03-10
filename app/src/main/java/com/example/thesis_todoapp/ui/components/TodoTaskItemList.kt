package com.example.thesis_todoapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thesis_todoapp.data.TodoItem
import com.example.thesis_todoapp.ui.theme.ThesisTodoAppTheme
import java.util.*

@Composable
fun TodoTaskItemList(
    list: List<TodoItem>,
    onCloseTask: (TodoItem) -> Unit,
    onCheckedChange: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
){
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState()
    ){
        items(items = list, key = {todoTask -> todoTask.id}){
            TodoTaskItem(
                taskName = it.label,
                isChecked = it.isChecked,
                onCheckedChange = { onCheckedChange(it) },
                onClose = { onCloseTask(it) }
            )
        }
    }
}

@Preview
@Composable
fun TodoTaskItemListPreview(){
    ThesisTodoAppTheme {
        TodoTaskItemList(List(3) { TodoItem(it,"Hello", false, Date() ) }, onCheckedChange = {}, onCloseTask = {} )
    }
}