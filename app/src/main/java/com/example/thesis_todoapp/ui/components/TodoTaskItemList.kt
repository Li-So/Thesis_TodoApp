package com.example.thesis_todoapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thesis_todoapp.R
import com.example.thesis_todoapp.data.TodoItem

@Composable
fun TodoTaskItemList(
    list: List<TodoItem>,
    onCloseTask: (TodoItem) -> Unit,
    onCheckedChange: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
){

    if(list.isNotEmpty()){
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(6.dp),
            state = rememberLazyListState()
        ) {
            items(items = list, key = { todoTask -> todoTask.id }) {
                if (list.size == 1) {
                    TodoTaskItem(
                        taskName = it.label,
                        isChecked = it.isChecked,
                        onCheckedChange = { onCheckedChange(it) },
                        onClose = { onCloseTask(it) },
                        modifier = modifier
                            .clip(RoundedCornerShape(17.dp))
                            .border(Dp.Hairline, Color.Black, RoundedCornerShape(17.dp))
                    )
                } else {
                    val shapeModifier = when(it.id){
                        list.first().id -> modifier.clip(
                            RoundedCornerShape(
                                topStart = 17.dp,
                                topEnd = 17.dp
                            )
                        )
                            .border(
                                Dp.Hairline, Color.Black, RoundedCornerShape(
                                    topStart = 17.dp,
                                    topEnd = 17.dp
                                )
                            )
                        list.last().id ->  modifier
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 17.dp,
                                    bottomEnd = 17.dp
                                )
                            )
                            .border(
                                Dp.Hairline, Color.Black, RoundedCornerShape(
                                    bottomStart = 17.dp,
                                    bottomEnd = 17.dp
                                )
                            )
                        else -> modifier
                            .border(Dp.Hairline, Color.Black)
                    }

                    TodoTaskItem(
                        taskName = it.label,
                        isChecked = it.isChecked,
                        onCheckedChange = { onCheckedChange(it) },
                        onClose = { onCloseTask(it) },
                        modifier = shapeModifier
                    )

                }
            }
        }
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "",
                tint = Color.White,
                modifier = modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
                )
            Text(
                text = stringResource(R.string.todo_list_is_empty_title),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                modifier = modifier
                    .padding(top = 20.dp)
            )
            Text(
                text = stringResource(R.string.todo_list_is_empty_description),
                color = Color.Gray,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                modifier = modifier
                    .padding(start = 50.dp, end = 50.dp)
            )
        }
    }
}