package com.example.figma_replicate.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.signup.R
import kotlinx.coroutines.delay

@Composable
fun ResendTextComponent(
    modifier: Modifier = Modifier,
    cooldownSeconds: Int = 10,
    onResend: () -> Unit
) {
    var isCooldown by remember { mutableStateOf(false) }
    var secondsLeft by remember { mutableStateOf(cooldownSeconds) }

    LaunchedEffect(isCooldown) {
        if (isCooldown) {
            while (secondsLeft > 0) {
                delay(1000)
                secondsLeft--
            }
            isCooldown = false
            secondsLeft = cooldownSeconds
        }
    }

    val displayText = if (isCooldown) "Resend code (${secondsLeft}s)" else "Resend code"

    Text(
        text = displayText,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFFFF7F50),
        fontFamily = FontFamily(Font(resId = R.font.montserrat_medium)),
        textDecoration = if (!isCooldown) TextDecoration.Underline else null,
        modifier= Modifier.clickable(enabled = !isCooldown){
            if (!isCooldown) {
                isCooldown = true
                onResend()
            }
        }
    )
} 