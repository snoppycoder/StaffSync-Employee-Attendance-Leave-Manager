package com.example.figma_replicate.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.figma_replicate.R

@Composable
fun InstructionTextComponent(email: String, modifier: Modifier = Modifier) {
    Text(
        text = "Enter the 6-digit code we sent to $email",
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF000000),
        fontFamily = FontFamily(Font(resId = R.font.montserrat_medium)),
        modifier = modifier
    )
} 