package com.example.figma_replicate.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController

import com.example.figma_replicate.ui.componen.ScheduleContent
import com.example.figma_replicate.ui.component.BottomNavBar

import com.example.figma_replicate.ui.component.ScheduleTopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleScreen(navController: NavController) {
    Scaffold(
        topBar = { ScheduleTopBar(navController) },
        bottomBar = { BottomNavBar(navController = navController as NavHostController) }
    ) { innerPadding ->
        ScheduleContent(innerPadding)
    }
}
