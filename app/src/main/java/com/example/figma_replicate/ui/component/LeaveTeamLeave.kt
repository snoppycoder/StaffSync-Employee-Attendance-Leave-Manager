package com.example.figma_replicate.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.figma_replicate.R
import com.example.figma_replicate.data.models.LeaveRequestResponse
import com.example.figma_replicate.viewModel.LeaveFormViewModel

@Composable

fun LeaveTeamLeave(
    request: LeaveRequestResponse,
    onApprove: (Int) -> Unit,
    onReject: (Int) -> Unit
) {
    val name = "John Doe" // Replace with request.userName if available
    val designation = "Software Engineer" // Replace with real value if available
    val startDate = request.startDate?.take(10)
    val endDate = request.endDate?.take(10)
    val applyDays = "${request.pointsDeduction} Days"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile picture"
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = name, fontWeight = FontWeight.Bold)
                    Text(text = designation, color = Color.Gray)
                }
            }

            Divider(thickness = 1.dp, color = Color(0xFFE0E0E0))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("$startDate - $endDate", fontWeight = FontWeight.Bold)
                Text("Apply Days\n$applyDays", fontWeight = FontWeight.Bold)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                request.id?.let { id ->
                    Button(onClick = { onApprove(id) }) {
                        Text("Approve")
                    }
                    Button(onClick = { onReject(id) }) {
                        Text("Reject")
                    }
                }

            }
        }
    }
}


@Composable
fun ListCardTeamLeave(viewModel: LeaveFormViewModel) {
    val leaveRequests by viewModel.request.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchLeaveRequest()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        leaveRequests.forEach { request ->
            LeaveTeamLeave(
                request = request,
                onApprove = { id -> viewModel.updateLeaveStatus(id, "APPROVED") },
                onReject = { id -> viewModel.updateLeaveStatus(id, "REJECTED") }
            )
        }
    }
}

