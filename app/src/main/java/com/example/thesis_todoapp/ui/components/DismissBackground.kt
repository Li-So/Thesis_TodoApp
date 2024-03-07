package com.example.thesis_todoapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DismissBackground(dismissState: DismissState){
    val color = when(dismissState.dismissDirection){
        DismissDirection.StartToEnd -> Color.Red
        DismissDirection.EndToStart -> Color.Green
        null -> Color.Transparent
    }
    val direction = dismissState.dismissDirection


    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = CenterVertically,
        horizontalArrangement = SpaceBetween
    ){
        if (direction == DismissDirection.StartToEnd){
            Text("No")
        }
        Spacer(modifier = Modifier)
        if (direction == DismissDirection.EndToStart) {
            Text("Yes")
        }
    }
}