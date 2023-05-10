package com.example.figma_replicate.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.ranges.coerceIn


@Composable
fun RecoveryStepProgressBar(
    currentStep: Int,
    modifier: Modifier = Modifier
) {
    val totalSteps = 3
    val progressFraction = (currentStep.coerceIn(1, totalSteps)).toFloat() / totalSteps
    val barWidth = 340.dp
    val barHeight = 8.dp
    val completedColor = Color(0xFFFF7F59)
    val incompleteColor = Color(0xFFBCBCBC)
    val stepTextColor = Color(0xFF14304A)

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$currentStep of $totalSteps",
                style = TextStyle(
                    color = stepTextColor,
                    fontSize = 16.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .width(barWidth)
                .height(barHeight)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(incompleteColor)
            )
            val animatedWidth by animateDpAsState(targetValue = barWidth * progressFraction, label = "progress")
            Box(
                modifier = Modifier
                    .height(barHeight)
                    .width(animatedWidth)
                    .background(completedColor)
            )
        }
    }
}

