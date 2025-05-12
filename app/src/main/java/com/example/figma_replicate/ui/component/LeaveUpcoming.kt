package com.example.figma_replicate.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.figma_replicate.viewModel.LeaveFormViewModel
import com.example.figma_replicate.viewModel.LeaveViewModel

@Composable

fun ListCardUpcoming(viewModel: LeaveFormViewModel) {
    val leaveRequests by viewModel.request.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchLeaveRequest()
    }
    if (viewModel.request != null){
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            if (isLoading) {
                CircularProgressIndicator()
            } else if (errorMessage != null) {
                Text("Error: $errorMessage", color = Color.Red)
            } else {
                leaveRequests.forEach { request ->
                    LeaveCardUpcoming(
                        dateRange = "${request.startDate} - ${request.endDate}",
                        applyDays = "${request.applyDays} Days",
                        leaveBalance = request.pointsDeduction.toString(),
                        status = request.status.toString(),
                        statusColor = Color.Yellow,
                        viewModel = viewModel
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
    else {
        Box(
            modifier=Modifier
                .fillMaxWidth()
        ){
            Text("You haven't applied for a leave Yet!")

        }

    }


}


@Composable
fun LeaveCardUpcoming(
    dateRange: String,
    applyDays: String,
    leaveBalance: String,
    status: String = "Pending",
    statusColor : Color = Color.Yellow,
    chronology : Chronology = Chronology.UPCOMING,
    viewModel: LeaveFormViewModel,

) {
    viewModel.fetchLeaveRequest()

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Date", color = Color.Black)
                Text(
                    status,
                    color = Color.hsl(55f, 0.82f, 0.56f),
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .background(statusColor.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
            Text(
                dateRange,
                fontWeight=FontWeight.Bold,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSurface
            )



            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Text("Leave Balance\n$leaveBalance", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
//



            }
        }
    }
}

