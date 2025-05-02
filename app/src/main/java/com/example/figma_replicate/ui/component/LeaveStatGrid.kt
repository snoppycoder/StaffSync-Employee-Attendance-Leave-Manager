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
    Row(modifier = Modifier.fillMaxWidth(), ) {
        StatCard(Color.hsl(215F,99F, 59F), "Present Employees", "20", Color.hsl(215f,99f, 59f), Modifier.weight(1f))
        StatCard(Color.hsl(78F,62F, 52F), "Absent Employees", "2", Color.hsl(78f,62f, 52f), Modifier.weight(1f))
    }
    Spacer(modifier = Modifier.height(8.dp))
    StatCard(Color.hsl(215F, 99F, 59F), "Leave Pending", "4", Color(0xFF008080), Modifier.fillMaxWidth())
}

@Composable
fun StatCard(
    backgroundColor: Color,
    title: String,
    value: String,
    valueColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, backgroundColor)
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
                color = valueColor,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
