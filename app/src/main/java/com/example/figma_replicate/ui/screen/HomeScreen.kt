package com.example.figma_replicate.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.figma_replicate.ui.component.*
import com.example.figma_replicate.viewModel.AttendanceViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: AttendanceViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getAttendance() // Fetch attendance records on screen load
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            ProfileView(navController = navController)
            DaysOfTheWeek()
            Spacer(modifier = Modifier.height(8.dp))
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AttendanceGrid(viewModel = viewModel)
            ListOfActivity(viewModel = viewModel)
        }
    }
}