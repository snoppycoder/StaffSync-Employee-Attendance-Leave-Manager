package com.example.figma_replicate.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaveDashboardTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("All leaves", color = Color(0xFFFF6B00)) },
        actions = {
            IconButton(onClick = { navController.navigate("notification") }, modifier = Modifier.size(20.dp),) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }
        }
    )
}

