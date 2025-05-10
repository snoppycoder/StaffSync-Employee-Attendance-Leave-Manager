package com.example.figma_replicate.ui.component
import androidx.compose.foundation.layout.*
import com.example.figma_replicate.R
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepIndicator(currentStep: Int) {
    val steps = listOf("Email", "Gender", "Birthday", "Password")

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        steps.forEachIndexed { index, label ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(
                        id = if (index <= currentStep) R.drawable.ic_ok else R.drawable.ic_circle
                    ),
                    contentDescription = label,
                    tint = if (index <= currentStep) Color(0xFFFF7043) else Color.LightGray,
                    modifier = Modifier.size(24.dp)
                )

            }
        }
    }
}
