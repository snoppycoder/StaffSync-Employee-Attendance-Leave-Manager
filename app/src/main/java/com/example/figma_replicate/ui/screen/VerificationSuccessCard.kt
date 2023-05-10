package com.example.figma_replicate.ui.screen

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.figma_replicate.R
import kotlin.collections.forEachIndexed
import kotlin.collections.map

@Composable
fun VerificationSuccessCard(
    subtitleText: String,
    onLoginClick: () -> Unit
) {
    // Overlay
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000)), // 50% black
        contentAlignment = Alignment.Center
    ) {
        // Card
        Card(
            modifier = Modifier
                .size(width = 336.dp, height = 474.dp)
                .clip(RoundedCornerShape(69.dp)),
            shape = RoundedCornerShape(69.dp),
            colors = CardDefaults.cardColors(containerColor =White),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Animation area (invisible rectangle)
                Box(
                    modifier = Modifier
                        .size(width = 183.dp, height = 184.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedVerificationCircles()
                }
                Spacer(modifier = Modifier.height(10.dp))
                // Title
                Text(
                    text = "Congratulations !",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        color = Color(0xFF000000)
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                // Subtitle
                Text(
                    text = subtitleText,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        color = Color(0xFF000000)
                    ),
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                // Login Button
                Button(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .width(288.dp)
                        .height(46.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF7F50),
                        contentColor = White
                    )
                ) {
                    Text(
                        text = "Go to Login",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun AnimatedVerificationCircles() {
    // Animation setup
    val duration = 3000 // ms for full cycle
    val infiniteTransition = rememberInfiniteTransition(label = "verification-circles")
    // For 3 medium and 2 small circles
    val delays = listOf(0, 400, 800, 1200, 1600) // ms, sequential
    val scales = delays.map { delay ->
        infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.5f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = duration
                    1f at delay
                    1.5f at delay + 300
                    1f at delay + 600
                },
                repeatMode = RepeatMode.Restart
            ), label = "circle-bounce-$delay"
        )
    }
    // Layout positions (relative to 183x184 box)
    val bigCenter = Pair(91.5f, 92f) // center
    val mediumPositions = listOf(
        Pair(18f, 18f), // top-left
        Pair(165f, 18f), // top-right
        Pair(91.5f, 166f) // bottom-center
    )
    val smallPositions = listOf(
//        Pair(41f, 92f), // left-mid
//        Pair(142f, 92f) // right-mid
        Pair(10f, 80f), // left-mid
        Pair(180f, 70f) // right-mid
    )
    Box(modifier = Modifier.fillMaxSize()) {
        // Small circles
        smallPositions.forEachIndexed { i, pos ->
            val currentSize = 9 * scales[i + 3].value
            Box(
                modifier = Modifier
                    .size(currentSize.dp)
                    .offset(x = (pos.first - currentSize / 2).dp, y = (pos.second - currentSize / 2).dp)
                    .background(Color(0xFFFF7F50), shape = RoundedCornerShape((currentSize / 2).dp))
            )

            // Medium circles
            mediumPositions.forEachIndexed { i, pos ->
                val currentSize = 18 * scales[i].value
                Box(
                    modifier = Modifier
                        .size(currentSize.dp)
                        .offset(x = (pos.first - currentSize / 2).dp, y = (pos.second - currentSize / 2).dp)
                        .background(Color(0xFFFF7F50), shape = RoundedCornerShape((currentSize / 2).dp))
                )
        // Big circle
        Box(
            modifier = Modifier
                .size(124.dp)
                .offset(x = (bigCenter.first - 62).dp, y = (bigCenter.second - 62).dp)
                .background(Color(0xFFFF7F50), shape = RoundedCornerShape(62.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                tint = White,
                modifier = Modifier.size(width = 34.dp, height = 40.dp)
            )
        }

        }

        }
    }
}

@Preview()
@Composable
fun VerificationSuccessCardPreview(){
    VerificationSuccessCard(
        subtitleText="Password reset successful. Taking you to login..",
        onLoginClick={}
    )
}