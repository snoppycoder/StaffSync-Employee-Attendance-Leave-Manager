package com.example.figma_replicate.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.lazy.grid.*

@Composable
fun Attendance() {
    val checkIns = listOf("item1", "item2", "item3", "item4")

    Column(modifier = Modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(checkIns) { check ->
                Box(modifier = Modifier.padding(15.dp)) {
                    Text(text = check)
                }
            }
        }
    }
}
