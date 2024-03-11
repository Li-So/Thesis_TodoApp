package com.example.thesis_todoapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissBoxValue: SwipeToDismissBoxValue){
    val color = when(dismissBoxValue){
        SwipeToDismissBoxValue.EndToStart-> Color.Red
        SwipeToDismissBoxValue.StartToEnd -> Color.Green
        SwipeToDismissBoxValue.Settled -> Color.Transparent
    }


    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = SpaceBetween
    ){
        Spacer(modifier = Modifier)
        if (dismissBoxValue == SwipeToDismissBoxValue.EndToStart) {
            Text("Delete")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewDismissBackground(){
    DismissBackground(dismissBoxValue = SwipeToDismissBoxValue.EndToStart)
}