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
fun LeaveCardList() {
    LeaveCard(
        dateRange = "Apr 15, 2025 - Apr 18, 2025",
        applyDays = "3 Days",
        leaveBalance = "16",
        approvedBy = "Martin Deo",
        status = "Rejected",
        statusColor = Color.Red
    )
    Spacer(modifier = Modifier.height(8.dp))
    LeaveCard(
        dateRange = "Mar 10, 2025 - Mar 12, 2025",
        applyDays = "2 Days",
        leaveBalance = "19",
        approvedBy = "Martin Deo",
        status = "Approved",
        statusColor = Color(0xFFB2DFDB)
    )
}

@Composable
fun LeaveCard(
    dateRange: String,
    applyDays: String,
    leaveBalance: String,
    approvedBy: String,
    status: String,
    statusColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Date", color = MaterialTheme.colorScheme.onSurface)
                Text(
                    status,
                    color = statusColor,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .background(statusColor.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Text(
                dateRange,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Apply Days\n$applyDays", color = MaterialTheme.colorScheme.onSurface)
                Text("Leave Balance\n$leaveBalance", color = MaterialTheme.colorScheme.onSurface)
                Text(
                    "Approved By\n$approvedBy",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

