package com.example.figma_replicate.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.figma_replicate.R
import com.example.figma_replicate.data.models.AttendanceRecord
import com.example.figma_replicate.viewModel.AttendanceState
import com.example.figma_replicate.viewModel.AttendanceViewModel
import androidx.compose.runtime.getValue

@Composable
fun AttendanceCard(
    status: String,
    time: String,
    task: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Icon(
                painter = if (status.lowercase().trim() == "check in")
                    painterResource(id = R.drawable.ic_checkin_)
                else
                    painterResource(id = R.drawable.ic_checkout),
                contentDescription = "Status Icon",
                modifier = Modifier.align(Alignment.TopStart)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(status, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text(time, style = MaterialTheme.typography.bodyLarge, color = Color.Black, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(task, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
            }
        }
    }
}

@Composable
fun AttendanceGrid(viewModel: AttendanceViewModel) {
    val state by viewModel.attendanceState // Directly use State delegation

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Your Attendance",
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(8.dp)
        )

        when (val result = state) {
            is AttendanceState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            is AttendanceState.Error -> {
                Text(
                    text = "Error: ${result.message}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is AttendanceState.Success -> {
                val records = when (val data = result.data) {
                    is List<*> -> data.filterIsInstance<AttendanceRecord>()
                    else -> emptyList()
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(records.size) { index ->
                        val record = records[index]
                        val status = if (record.checkIn != null && record.checkOut == null) "Check In" else "Check Out"
                        val time = record.checkIn ?: record.checkOut ?: "N/A"
                        val task = when {
                            record.attendance == null -> "Pending"
                            record.attendance.name == "PRESENT" -> "On Time"
                            else -> "Absent"
                        }
                        AttendanceCard(status, time, task)
                    }
                }
                if (records.isEmpty()) {
                    Text(
                        text = "No attendance records",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
            is AttendanceState.Idle -> {
                Text(
                    text = "Loading attendance...",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}