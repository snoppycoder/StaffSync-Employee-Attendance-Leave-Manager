package com.example.figma_replicate.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import com.example.figma_replicate.ui.component.BackArrowComponent
import com.example.figma_replicate.ui.component.BottomTextComponent
import com.example.figma_replicate.ui.component.CreateAccountTextComponent
import com.example.figma_replicate.ui.component.ImageComponent
import com.example.figma_replicate.ui.component.PasswordComponent
import com.example.figma_replicate.ui.component.SignUpProgressBarComponent

@Composable
fun PasswordScreen(
    onBack: () -> Unit,
    onFinish: (String) -> Unit,
    onStepClick: (Int) -> Unit,
    onLogin: () -> Unit
) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier=Modifier.height(4.dp))
                // Back arrow
                BackArrowComponent(onBack = onBack)
                Spacer(modifier = Modifier.height(4.dp))
                // Illustration/Image
                ImageComponent()
                Spacer(modifier = Modifier.height(2.dp))

                // Create account text
                CreateAccountTextComponent()
                Spacer(modifier = Modifier.height(4.dp))

                // Progress bar
                SignUpProgressBarComponent(
                    steps = listOf("Email", "Name", "Birthday", "Gender", "Pass"),
                    currentStep = 4,
                    onStepClick = onStepClick
                )
//                Spacer(modifier = Modifier.height(32.dp))
// Password fields and Finish button
                PasswordComponent(onFinish = onFinish)
                Spacer(modifier = Modifier.height(6.dp))

                // Bottom text
                BottomTextComponent(onLogin = onLogin)
            }
        }

