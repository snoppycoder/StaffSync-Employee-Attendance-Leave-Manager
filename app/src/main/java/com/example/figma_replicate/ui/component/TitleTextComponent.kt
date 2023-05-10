package com.example.figma_replicate.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.signup.R

@Composable
fun TitleTextComponent(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Enter Confirmation Code",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF36454F),
        fontFamily = FontFamily(Font(resId =R.font.montserrat_bold)),
        modifier = modifier,
        textAlign = TextAlign.Center
    )
} 