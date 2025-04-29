package com.example.figma_replicate.ui.screen

import com.example.figma_replicate.ui.component.DaysOfTheWeek
import com.example.figma_replicate.ui.component.ProfileView
import com.example.figma_replicate.ui.component.Attendance
import android.os.Build
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        ProfileView()
        DaysOfTheWeek()
        Attendance()
    }
}
