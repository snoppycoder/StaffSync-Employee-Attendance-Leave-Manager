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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.figma_replicate.navigation.Routes

@Composable

fun LeaveStatGrid() {
    Row(modifier = Modifier.fillMaxWidth()) {
        StatCard(
            backgroundColor = Color.hsl(215f, 0.99f, 0.59f),
            title = "Present Employees",
            value = "20",
            valueColor = Color.hsl(215f, 0.99f, 0.59f),
            modifier = Modifier.weight(1f)
        )

        StatCard(
            backgroundColor = Color.hsl(78f, 0.62f, 0.52f),
            title = "Absent Employees",
            value = "2",
            valueColor = Color.hsl(78f, 0.62f, 0.52f),
            modifier = Modifier.weight(1f)
        )
    }

    Spacer(modifier = Modifier.height(8.dp))

    StatCard(
        backgroundColor = Color.hsl(215f, 0.99f, 0.59f),
        title = "Leave Pending",
        value = "4",
        valueColor = Color(0xFF008080),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun StatCard(
    backgroundColor: Color,
    title: String,
    value: String,
    border: Color = Color.Black,
    valueColor: Color = border,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, border)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                value,
                color = border,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
