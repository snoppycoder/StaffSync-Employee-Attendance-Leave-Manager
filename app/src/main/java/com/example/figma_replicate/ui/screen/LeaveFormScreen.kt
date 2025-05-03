package com.example.figma_replicate.ui.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.navigation.NavController

import com.example.figma_replicate.ui.component.LeaveFormContent

@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun LeaveFormScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Apply Leave") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->

        LeaveFormContent(innerPadding)
    }
}