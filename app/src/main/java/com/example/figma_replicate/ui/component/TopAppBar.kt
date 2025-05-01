package com.example.figma_replicate.ui.component

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
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment

import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bar(title:String, navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth()
        , verticalAlignment = Alignment.CenterVertically


    ) {
        IconButton(onClick = {navController.popBackStack()}) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Go Back")

        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text=title, fontWeight = FontWeight.SemiBold)
        }

    }
}
