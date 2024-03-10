package com.example.thesis_todoapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thesis_todoapp.ui.theme.ThesisTodoAppTheme


@Composable
fun TodoTaskItem(taskName: String, onClose: () -> Unit, modifier: Modifier = Modifier){
    var checkedState by rememberSaveable { mutableStateOf(false) }

    TodoTaskItem(
        taskName = taskName,
        checked = checkedState,
        onCheckedChange = { newValue -> checkedState = newValue },
        onClose =  onClose ,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
){
    val dismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if(it == SwipeToDismissBoxValue.EndToStart){
                onClose()
                true
            } else false
        }
    )

    SwipeToDismissBox(
        state = dismissBoxState,
        backgroundContent = {
            DismissBackground(dismissBoxValue = dismissBoxState.currentValue)
        },
        enableDismissFromEndToStart = true
    ) {


        Row(
            modifier = modifier
                .fillMaxWidth()
                .border(3.dp, Color.Black, RectangleShape),
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(checked = checked, onCheckedChange = onCheckedChange)
            Text(
                modifier = Modifier,
                text = taskName,
                textDecoration = when(checked){
                    true -> TextDecoration.LineThrough
                    false -> null
                }
            )
        }
    }



}

@Preview
@Composable
fun TodoTaskItemPreview() {
    ThesisTodoAppTheme {
        TodoTaskItem("Hello", false, {}, {})
    }
}