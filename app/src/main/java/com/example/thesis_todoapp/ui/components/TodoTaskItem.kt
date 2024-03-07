package com.example.thesis_todoapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
){
    val dismissState = rememberDismissState()

    SwipeToDismiss(
        state = dismissState,
        background = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> Color.Yellow
                    DismissValue.DismissedToEnd -> Color.Green
                    DismissValue.DismissedToStart -> Color.Red
                }, label = "Animate"
            )
        }
    ) {
        if(dismissState.dismissDirection == DismissDirection.EndToStart){
            onClose()
        }

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