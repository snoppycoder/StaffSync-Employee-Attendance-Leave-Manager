package com.example.figma_replicate.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.figma_replicate.viewModel.LeaveState
import com.example.figma_replicate.viewModel.LeaveViewModel

@Composable
fun LeaveStatGrid(viewModel: LeaveViewModel = hiltViewModel()) {
    val state by viewModel.leaveState
    
    LaunchedEffect(Unit) {
        viewModel.fetchLeaveStats()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        when (state) {
            is LeaveState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is LeaveState.Error -> {
                Text(
                    text = "Error: ${(state as LeaveState.Error).message}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is LeaveState.Success -> {
                val stats = (state as LeaveState.Success).stats
                Row(modifier = Modifier.fillMaxWidth()) {
                    StatCard(
                        backgroundColor = Color.hsl(216f, 1f, 0.98f),
                        title = "Leave Balance",
                        border = Color.hsl(215f, 0.99f, 0.59f),
                        value = stats.leaveBalance.toString(),
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        backgroundColor = Color.hsl(0f, 0f, 0.95f),
                        title = "Approved Leaves",
                        value = stats.leaveApproved.toString(),
                        border = Color.hsl(5f, 1f, 0.73f),
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    StatCard(
                        backgroundColor = Color.hsl(83f, 0.67f, 0.98f),
                        title = "Leave Pending",
                        value = stats.leavePending.toString(),
                        border = Color.hsl(78f, 0.62f, 0.52f),
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        backgroundColor = Color.hsl(0f, 0f, 0.95f),
                        title = "Leave Cancelled",
                        value = stats.leaveCancelled.toString(),
                        border = Color.hsl(5f, 1f, 0.73f),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            is LeaveState.Idle -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
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
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = value,
                color = border,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}