package com.example.figma_replicate.ui.screen

import ListOfActivity
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
import androidx.navigation.NavController
import com.example.figma_replicate.ui.component.*
import com.example.figma_replicate.viewModel.ResourceViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController,
               viewModel: ResourceViewModel = hiltViewModel()
) {
    val user by viewModel.employee.collectAsState()



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
            ListOfActivity()
        }
    }

}

