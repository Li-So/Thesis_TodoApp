package com.example.thesis_todoapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
@ExperimentalMaterial3Api
fun SheetAddTodo(
    content: @Composable() () -> Unit
){
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Box(modifier = Modifier
        .fillMaxWidth()){
        IconButton(
            onClick = { isSheetOpen = true },
            content = {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "",
                    modifier = Modifier
                        .size(44.dp)
                )
                      },
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
    }

    if(isSheetOpen){
        ModalBottomSheet(
            modifier = Modifier
                .fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = { isSheetOpen = false }
        )
        {
            content()
        }
    }
}