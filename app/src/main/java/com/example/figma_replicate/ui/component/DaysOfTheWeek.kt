package com.example.figma_replicate.ui.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DaysOfTheWeek() {
    val curr = LocalDate.now()
    val currFormat = curr.format(DateTimeFormatter.ofPattern("dd"))
    val start = curr.with(java.time.DayOfWeek.MONDAY)
    val days = mutableListOf<String>()
    val months = mutableListOf<String>()
    for (i in 0..6) {
        val day = start.plusDays(i.toLong())
        days.add(day.format(DateTimeFormatter.ofPattern("dd")))
        months.add(day.format(DateTimeFormatter.ofPattern("MMM")))
    }
    val combinedList = days.zip(months)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(combinedList.size) { index ->
            val (day, month) = combinedList[index]
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (day == currFormat) Color(0xFFFF7F50)
                        else MaterialTheme.colorScheme.surface
                    )
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    text = day,
                    fontWeight = FontWeight.Bold,
                    color = if (day == currFormat) Color.White else MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = month,
                    color = if (day == currFormat) Color.White else MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}