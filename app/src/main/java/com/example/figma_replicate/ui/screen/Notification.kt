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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.figma_replicate.ui.component.Bar
import com.example.figma_replicate.ui.component.ListOfActivity


@Composable
fun Notification(title:String, navController: NavController) {
    Bar("Navigation", navController)

}
