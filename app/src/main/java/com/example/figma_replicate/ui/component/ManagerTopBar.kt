package com.example.figma_replicate.ui.component

import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("All leaves") },
        actions = {
            IconButton(onClick = { navController.navigate("notification") }) {
                Icon(

                    imageVector = Icons.Filled.Notifications,
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }

        }
    )
}

