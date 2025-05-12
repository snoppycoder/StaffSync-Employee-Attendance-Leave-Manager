package com.example.figma_replicate.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.AddHoliday
import com.example.figma_replicate.data.models.Holiday
import com.example.figma_replicate.viewModel.HolidayViewModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HolidayScreen(viewModel: HolidayViewModel = hiltViewModel(), authPrefs: AuthPrefs) {
    val holidays by viewModel.holidays.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    val isManager = authPrefs.isManager()  // Check if the user is a manager

    // State to manage the dialog visibility and inputs
    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Holidays") })
        },
        floatingActionButton = {
            // Show the "Add Holiday" button only if the user is a manager
            if (isManager) {
                FloatingActionButton(onClick = { showDialog = true }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Holiday")
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                error != null -> {
                    Text(
                        text = error ?: "Unknown error",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                holidays.isEmpty() -> {
                    Text(
                        text = "No holidays found.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(modifier = Modifier.padding(16.dp)) {
                        items(holidays) { holiday ->
                            HolidayItem(holiday = holiday)
                        }
                    }
                }
            }
        }

        // Add Holiday Dialog
        if (showDialog) {
            HolidayDialog(
                title = title,
                startDate = startDate,
                endDate = endDate,
                onTitleChange = { title = it },
                onStartDateChange = { startDate = it },
                onEndDateChange = { endDate = it },
                onDismiss = { showDialog = false },
                onSave = {
                    val holiday = AddHoliday(title = title, startDate = startDate, endDate = endDate)
                    val token = authPrefs.getToken()
                    viewModel.addHoliday(token.toString(), holiday)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun HolidayDialog(
    title: String,
    startDate: String,
    endDate: String,
    onTitleChange: (String) -> Unit,
    onStartDateChange: (String) -> Unit,
    onEndDateChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Holiday") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text("Holiday Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = startDate,
                    onValueChange = onStartDateChange,
                    label = { Text("Start Date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = endDate,
                    onValueChange = onEndDateChange,
                    label = { Text("End Date (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onSave) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HolidayItem(holiday: Holiday) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = holiday.title ?: "Untitled Holiday",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "From: ${formatIsoDate(holiday.startDate)}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "To: ${formatIsoDate(holiday.endDate)}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Text(
                text = "Scheduled",
                color = Color(0xFF4CAF50),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Divider(color = Color.LightGray, thickness = 0.5.dp, modifier = Modifier.padding(top = 8.dp))
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun formatIsoDate(dateString: String?): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(dateString)
        val formatter = DateTimeFormatter.ofPattern("EEE, MMM dd")
        zonedDateTime.format(formatter)
    } catch (e: Exception) {
        "Invalid date"
    }
}
