package com.example.figma_replicate.ui.component

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.figma_replicate.viewModel.LeaveFormUiState
import com.example.figma_replicate.viewModel.LeaveFormViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CalendarField(
    label: String,
    selectedDate: String,
    minDateMillis: Long? = null,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = remember {
        DatePickerDialog(context, { _, y, m, d ->
            val formattedDate = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
                .format(GregorianCalendar(y, m, d).time)
            onDateSelected(formattedDate)
        }, year, month, day).apply {
            minDateMillis?.let { datePicker.minDate = it }
        }
    }

    Card(
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { datePickerDialog.show() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = label, color = Color(0xFFFF6D00))
                Text(text = selectedDate, color = Color.Black)
            }
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Calendar",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LeaveFormContent(innerPadding: PaddingValues, viewModel: LeaveFormViewModel = hiltViewModel()) {
    // Collect uiState as State
    val uiState by viewModel.uiState.collectAsState()

    val sdf = remember { SimpleDateFormat("MMM d, yyyy", Locale.getDefault()) }

    val minDateMillis = remember(viewModel.startDate) {
        try {
            sdf.parse(viewModel.startDate)?.time ?: 0L
        } catch (e: Exception) {
            0L
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            OutlinedTextField(
                value = viewModel.leaveType,
                onValueChange = { viewModel.updateLeaveType(it) }, // Updated to new setter
                label = { Text("Leave Type") },
                modifier = Modifier.fillMaxWidth()
            )

            CalendarField("Start Date", selectedDate = viewModel.startDate) {
                viewModel.updateStartDate(it) // Updated to new setter
            }

            CalendarField("End Date", selectedDate = viewModel.endDate, minDateMillis = minDateMillis) {
                viewModel.updateEndDate(it) // Updated to new setter
            }

            OutlinedTextField(
                value = viewModel.reason,
                onValueChange = { viewModel.updateReason(it) }, // Updated to new setter
                label = { Text("Reason") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Button(
                onClick = { viewModel.submitLeaveRequest() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF7F50)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit", fontWeight = FontWeight.Bold)
            }

            when (uiState) {
                is LeaveFormUiState.Loading -> Text("Submitting...", color = Color.Gray)
                is LeaveFormUiState.Success -> Text("Leave request submitted!", color = Color.Green)
                is LeaveFormUiState.Error -> Text(
                    "Error: ${(uiState as LeaveFormUiState.Error).message}",
                    color = Color.Red
                )
                is LeaveFormUiState.Idle -> {} // No action needed
            }
        }
    }
}