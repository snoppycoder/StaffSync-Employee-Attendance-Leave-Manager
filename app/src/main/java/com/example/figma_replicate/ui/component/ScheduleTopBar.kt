package com.example.figma_replicate.ui.component

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
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.figma_replicate.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("All leaves") },
        actions = {
            IconButton(onClick = { navController.navigate("notification") }) {
                Icon(

                    imageVector = Icons.Filled.Notifications,
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }
            IconButton(onClick = { navController.navigate("apply_leave") }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    modifier = Modifier.size(20.dp),
                    contentDescription = "Add-leave",
                    tint = Color.Black
                )
            }
        }
    )
}

