package com.example.figma_replicate.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.figma_replicate.ui.component.*
import com.example.figma_replicate.viewModel.AttendanceViewModel
import com.example.figma_replicate.viewModel.ResourceViewModel
import androidx.compose.runtime.LaunchedEffect

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavController,
    resourceViewModel: ResourceViewModel = hiltViewModel(),
    attendanceViewModel: AttendanceViewModel = hiltViewModel()
) {
    val user by resourceViewModel.employee.collectAsState()
    val attendanceState by attendanceViewModel.attendanceState
    val scrollState = rememberScrollState()




    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)

    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            ProfileView(navController = navController, user)
            DaysOfTheWeek()
            Spacer(modifier = Modifier.height(8.dp))
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AttendanceGrid()
            ListOfActivity(viewModel = attendanceViewModel)
        }
    }

}

