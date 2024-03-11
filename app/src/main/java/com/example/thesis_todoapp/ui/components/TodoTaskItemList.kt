package com.example.thesis_todoapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
        contentPadding = PaddingValues(start = 6.dp, end = 6.dp),
        state = rememberLazyListState()
    ){
        items(items = list, key = {todoTask -> todoTask.id}){
            if(it.id == list.last().id){
                TodoTaskItem(
                    taskName = it.label,
                    isChecked = it.isChecked,
                    onCheckedChange = { onCheckedChange(it) },
                    onClose = { onCloseTask(it) },
                    modifier = modifier
                        .clip(RoundedCornerShape(
                            bottomStart = 17.dp,
                            bottomEnd = 17.dp)
                        )
                        .border(Dp.Hairline, Color.Black, RoundedCornerShape(
                            bottomStart = 17.dp,
                            bottomEnd = 17.dp)
                        )
                )
            } else if(it.id == list.first().id) {
                TodoTaskItem(
                    taskName = it.label,
                    isChecked = it.isChecked,
                    onCheckedChange = { onCheckedChange(it) },
                    onClose = { onCloseTask(it) },
                    modifier = modifier
                        .clip(RoundedCornerShape(
                            topStart = 17.dp,
                            topEnd = 17.dp)
                        )
                        .border(Dp.Hairline, Color.Black, RoundedCornerShape(
                            topStart = 17.dp,
                            topEnd = 17.dp)
                        )
                )
            } else {
                TodoTaskItem(
                    taskName = it.label,
                    isChecked = it.isChecked,
                    onCheckedChange = { onCheckedChange(it) },
                    onClose = { onCloseTask(it) },
                    modifier = modifier
                        .border(Dp.Hairline, Color.Black)
                )
            }
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