package com.example.figma_replicate.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.figma_replicate.R

@Composable
fun OtpInputComponent(
    otpValue: List<String>,
    onOtpChange:(List<String>) -> Unit
) {
    val otpLength = 4
    val boxSize = 55.dp
    val boxGap = 22.dp
    val cornerRadius = 9.dp
    val borderWidth = 1.dp
    val borderColor = Color(0xFF000000)
    val focusedBorderColors = listOf(Color(0xFFFF7F50), Color(0xFFFFB199))
    val textColor = Color(0xFFFF7F50)
    val cursorColor = Color(0xFFFF7F50)
    val fontFamily = FontFamily(Font(resId = R.font.montserrat_medium))
    val focusRequesters = List(otpLength) { remember { FocusRequester() } }
    val focusManager = LocalFocusManager.current
    var otpValue by remember { mutableStateOf(List(otpLength) { "" }) }
    var focusedIndex by remember { mutableStateOf(0) }

    // Animation for circulating gradient
    val infiniteTransition = rememberInfiniteTransition(label = "otp-gradient")
    val animatedSweep by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = LinearEasing), // fast
            repeatMode = RepeatMode.Restart
        ), label = "sweep"
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(boxGap),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentWidth()
    ) {
        otpValue.forEachIndexed { index, value ->
            key(index) {
                val isFocused = focusedIndex == index
                BasicTextField(
                    value = value,
                    onValueChange = { input ->
                        val filtered = input.filter { it.isDigit() }.take(1)
                        if (filtered.isNotEmpty()) {
                            val newOtp = otpValue.toMutableList()
                            newOtp[index] = filtered
                            otpValue = newOtp
                            if (index < otpLength - 1) {
                                focusedIndex = index + 1
                                focusRequesters[index + 1].requestFocus()
                            } else {
                                focusManager.clearFocus()
                            }
                        } else if (value.isNotEmpty() && filtered.isEmpty()) {
                            // Handle backspace
                            val newOtp = otpValue.toMutableList()
                            newOtp[index] = ""
                            otpValue = newOtp
                            if (index > 0) {
                                focusedIndex = index - 1
                                focusRequesters[index - 1].requestFocus()
                            }
                        }
                    },
                    modifier = Modifier
                        .size(boxSize)
                        .focusRequester(focusRequesters[index])
                        .focusable()
                        .drawBehind {
                            if (isFocused) {
                                // Animated circulating gradient border
                                val sweep = animatedSweep
                                val brush = Brush.sweepGradient(
                                    colors = focusedBorderColors,
                                    center = center
                                )
                                drawRoundRect(
                                    brush = brush,
                                    size = size,
                                    cornerRadius = CornerRadius(
                                        cornerRadius.toPx()
                                    ),
                                    style = Stroke(width = borderWidth.toPx())
                                )
                            } else {
                                // Default border
                                drawRoundRect(
                                    color = borderColor,
                                    size = size,
                                    cornerRadius = CornerRadius(
                                        cornerRadius.toPx()
                                    ),
                                    style = Stroke(width = borderWidth.toPx())
                                )
                            }
                        }
                        .background(Color.White, RoundedCornerShape(cornerRadius)),
                    textStyle = TextStyle(
                        color = textColor,
                        fontSize = 16.sp,
                        fontFamily = fontFamily,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    cursorBrush = Brush.linearGradient(listOf(cursorColor, cursorColor)),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            innerTextField()
                        }
                    }
                )
            }
        }
    }
}
