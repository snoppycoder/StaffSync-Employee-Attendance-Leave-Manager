package com.example.figma_replicate.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.figma_replicate.ui.component.ScheduleContent
import com.example.figma_replicate.ui.component.BottomNavBar
import com.example.figma_replicate.ui.component.ScheduleTopBar
import com.example.figma_replicate.viewModel.LeaveViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleScreen(navController: NavController) {
    val viewModel: LeaveViewModel = hiltViewModel()

    Scaffold(
        topBar = { ScheduleTopBar(navController) }
    ) { innerPadding ->
        ScheduleContent(innerPadding)
    }
}