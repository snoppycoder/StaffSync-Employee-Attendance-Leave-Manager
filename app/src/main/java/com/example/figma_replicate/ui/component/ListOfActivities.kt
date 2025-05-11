package com.example.figma_replicate.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.figma_replicate.R
import com.example.figma_replicate.data.models.AttendanceRecord
import com.example.figma_replicate.data.models.AttendanceStatus
import com.example.figma_replicate.viewModel.AttendanceState
import com.example.figma_replicate.viewModel.AttendanceViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ListOfActivity(viewModel: AttendanceViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state by viewModel.attendanceState
    var isCheckedIn by remember { mutableStateOf(false) }
    var localRecords by remember { mutableStateOf<List<AttendanceRecord>>(emptyList()) }

    // Update local records when state changes
    LaunchedEffect(state) {
        if (state is AttendanceState.Success) {
            localRecords = (state as AttendanceState.Success).data
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Your Activity",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        when (state) {
            is AttendanceState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is AttendanceState.Error -> {
                Text(
                    text = "Error: ${(state as AttendanceState.Error).message}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                val sortedRecords = localRecords.sortedByDescending { it.checkIn?.toLongOrNull() ?: 0L }
                if (sortedRecords.isEmpty()) {
                    Text(
                        text = "No attendance records",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                } else {
                    sortedRecords.forEach { activity ->
                        ActivityCard(activity = activity)
                    }
                }
            }
        }

        Button(
            onClick = {
                val timestamp = System.currentTimeMillis().toString()
                if (!isCheckedIn) {
                    // Create new check-in record
                    val newRecord = AttendanceRecord(
                        id = localRecords.size + 1,
                        checkIn = timestamp,
                        checkOut = null,
                        attendance = AttendanceStatus.PRESENT,
                        date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
                        createdAt = timestamp,
                        user = null
                    )
                    localRecords = localRecords + newRecord
                    isCheckedIn = true
                    viewModel.checkIn()
                    Toast.makeText(context, "Checked in successfully", Toast.LENGTH_SHORT).show()
                } else {
                    // Update the last record with check-out
                    if (localRecords.isNotEmpty()) {
                        val lastRecord = localRecords.last()
                        if (lastRecord.checkOut == null) {
                            val updatedRecord = lastRecord.copy(checkOut = timestamp)
                            localRecords = localRecords.dropLast(1) + updatedRecord
                            isCheckedIn = false
                            viewModel.checkOut()
                            Toast.makeText(context, "Checked out successfully", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7F50)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = if (!isCheckedIn) "Check In" else "Check Out",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ActivityCard(activity: AttendanceRecord) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        id = if (activity.checkOut == null) R.drawable.ic_checkin_ else R.drawable.ic_checkout
                    ),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = if (activity.checkOut == null) "Check In" else "Check Out",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = formatTimestamp(activity.checkIn ?: activity.checkOut),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = formatTime(activity.checkIn ?: activity.checkOut),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}

private fun formatTimestamp(timestamp: String?): String {
    if (timestamp == null) return "Unknown date"
    return try {
        val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val date = Date(timestamp.toLong())
        sdf.format(date)
    } catch (e: Exception) {
        "Invalid date"
    }
}

private fun formatTime(timestamp: String?): String {
    if (timestamp == null) return "N/A"
    return try {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = Date(timestamp.toLong())
        sdf.format(date)
    } catch (e: Exception) {
        "N/A"
    }
}
