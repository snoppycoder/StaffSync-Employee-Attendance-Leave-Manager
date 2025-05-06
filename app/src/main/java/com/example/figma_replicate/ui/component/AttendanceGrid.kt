package com.example.figma_replicate.ui.component
import com.example.figma_replicate.R

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight


@Composable()
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
                .fillMaxWidth().padding(4.dp)

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
                Text(status, style = MaterialTheme.typography.bodyMedium, color=Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text(time, style = MaterialTheme.typography.bodyLarge, color=Color.Black, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(task, style = MaterialTheme.typography.bodyMedium, color=Color.Black)
            }
        }
    }
}

    @Composable
    fun AttendanceGrid() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Text(
                text = "Your Attendance",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(8.dp)

            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),

                ) {
                val attendanceList = listOf(
                    Triple("Check In", "10:45PM", "On Time"),
                    Triple("Check Out", "6:30PM", "Left Early"),
                    Triple("Check In", "9:00AM", "Late"),
                    Triple("Check Out", "5:00PM", "On Time")
                )

                items(attendanceList.size) { item ->
                    val (status, time, task) = attendanceList[item]
                    AttendanceCard(status, time, task)
                }
            }
        }
    }

