package com.example.figma_replicate.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.signup.R
import kotlin.text.toRegex

@Composable
fun RecoveryActionButton(
    buttonText: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF7F50),
            contentColor = Color.White,
            disabledContainerColor = Color(0xFFFF7F50).copy(alpha = 0.5f),
            disabledContentColor = Color.White.copy(alpha = 0.7f)
        ),
        modifier = modifier
            .width(288.dp)
            .height(46.dp)
    ) {
        Text(
            text = buttonText,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(resId = R.font.montserrat_bold))
            )
        )
    }
    Spacer(modifier= Modifier.height(20.dp))
}

// Email validation utility
fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()
    return emailRegex.matches(email)
} 