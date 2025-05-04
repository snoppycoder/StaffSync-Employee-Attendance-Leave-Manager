package com.example.figma_replicate.ui.component

import com.example.figma_replicate.R
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

@Composable()
fun AttendanceCard(
    status: String,
    time: String,
    task: String
){
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Text("Your Attendance")

    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = if (status.lowercase().replace(" ", "") == "checkin")
                        painterResource(id = R.drawable.ic_checkin_)
                    else
                        painterResource(id = R.drawable.ic_checkout),
                    contentDescription = status
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = status,
                    style = MaterialTheme.typography.bodyMedium,color = Color.Black
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()

                , horizontalArrangement = Arrangement.Center

            ){
                Text(time, style = MaterialTheme.typography.bodyLarge, color = Color.Black,fontWeight= FontWeight.Bold)

            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
                ,horizontalArrangement = Arrangement.Center
            ){
                Text(task, style = MaterialTheme.typography.bodySmall, color = Color.Black)

            }
        }

    }
}
@Composable
fun AttendanceGrid() {
    Column(modifier = Modifier
        .padding(8.dp)) {

        Text(
            text = "Today's Attendance",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 8.dp)

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


