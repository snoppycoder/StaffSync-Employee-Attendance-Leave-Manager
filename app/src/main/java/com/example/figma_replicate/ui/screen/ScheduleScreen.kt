package com.example.figma_replicate.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.figma_replicate.navigation.Routes
import com.example.figma_replicate.ui.componen.ScheduleContent
import com.example.figma_replicate.ui.component.BottomNavBar
import com.example.figma_replicate.ui.component.LeaveDashboardContent
import com.example.figma_replicate.ui.component.LeaveDashboardTopBar
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
