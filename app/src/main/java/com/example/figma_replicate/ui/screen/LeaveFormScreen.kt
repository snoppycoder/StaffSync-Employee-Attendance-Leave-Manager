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
import com.example.figma_replicate.ui.component.EditProfileContent

@Composable
@OptIn(ExperimentalMaterial3Api::class)

fun ProfileScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->

        EditProfileContent(innerPadding)
    }
}