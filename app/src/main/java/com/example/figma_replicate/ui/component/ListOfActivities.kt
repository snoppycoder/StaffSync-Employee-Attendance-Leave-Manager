package com.example.figma_replicate.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.figma_replicate.viewModel.AttendanceState
import com.example.figma_replicate.viewModel.AttendanceViewModel
import androidx.compose.runtime.getValue
@Composable
fun ListOfActivity(viewModel: AttendanceViewModel = hiltViewModel()) {
    val state by viewModel.attendanceState // Directly use State delegation
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Your Activity",
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        when (val result = state) {
            is AttendanceState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            is AttendanceState.Error -> {
                Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                Text(
                    text = "Error: ${result.message}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                LaunchedEffect(Unit) {
                    viewModel.resetState()
                }
            }
            is AttendanceState.Success -> {
                when (val data = result.data) {
                    is List<*> -> {
                        val records = data.filterIsInstance<AttendanceRecord>()
                        if (records.isEmpty()) {
                            Text(
                                text = "No attendance records",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        } else {
                            records.forEach { activity ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            id = if (activity.checkIn != null && activity.checkOut == null)
                                                R.drawable.ic_checkin_
                                            else
                                                R.drawable.ic_checkout
                                        ),
                                        contentDescription = if (activity.checkIn != null && activity.checkOut == null) "Check In" else "Check Out"
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = activity.checkIn ?: activity.checkOut ?: "N/A",
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = activity.date ?: "Unknown date",
                                            fontWeight = FontWeight.Light,
                                            fontSize = 10.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = activity.attendance?.name ?: "N/A",
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                    else -> {
                        // Handle check-in/check-out success
                        Toast.makeText(context, "Action completed", Toast.LENGTH_SHORT).show()
                        LaunchedEffect(Unit) {
                            viewModel.getAttendance() // Refresh attendance list
                        }
                    }
                }
            }
            is AttendanceState.Idle -> {
                // Initial state, no action needed
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.checkIn() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF7F50)
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(text = "Check In", fontWeight = FontWeight.Bold)
            }
            Button(
                onClick = { viewModel.checkOut() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF7F50)
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(text = "Check Out", fontWeight = FontWeight.Bold)
            }
        }
    }
}