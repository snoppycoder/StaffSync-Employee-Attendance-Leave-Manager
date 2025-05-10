package com.example.figma_replicate.ui.screen

import ListOfActivity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.figma_replicate.ui.component.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController) {
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
            AttendanceGrid()
            ListOfActivity()
        }
    }
}