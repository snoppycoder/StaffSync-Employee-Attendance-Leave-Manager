package com.example.figma_replicate.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.ui.component.BottomNavBar
import com.example.figma_replicate.ui.component.LeaveDashboardContent
import com.example.figma_replicate.ui.component.LeaveDashboardTopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LeaveDashboardScreen(navController: NavController, ) {
    Scaffold(
        topBar = { LeaveDashboardTopBar(navController) },
    ) { innerPadding ->
        LeaveDashboardContent(innerPadding)
    }
}
