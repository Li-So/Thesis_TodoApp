package com.example.thesis_todoapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thesis_todoapp.ui.theme.ThesisTodoAppTheme


@Composable
fun TodoTaskItem(taskName: String, modifier: Modifier = Modifier){
    var checkedState by remember { mutableStateOf(false) }

    TodoTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose = { /*TODO*/ })
}

@Composable
fun TodoTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
    }

    Text(
        modifier = Modifier
            .padding(start = 16.dp),
        text = taskName)
}

@Preview
@Composable
fun TodoTaskItemPreview() {
    ThesisTodoAppTheme {
        TodoTaskItem("Hello", false, {}, {})
    }
}