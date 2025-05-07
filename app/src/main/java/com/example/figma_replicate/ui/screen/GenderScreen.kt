package com.example.figma_replicate.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.figma_replicate.ui.component.BackArrowComponent
import com.example.figma_replicate.ui.component.BottomTextComponent
import com.example.figma_replicate.ui.component.ContinueButtonComponent
import com.example.figma_replicate.ui.component.CreateAccountTextComponent
import com.example.figma_replicate.ui.component.GenderDropdownComponent
import com.example.figma_replicate.ui.component.ImageComponent
import com.example.figma_replicate.ui.component.SignUpProgressBarComponent

@Composable
fun GenderScreen(
    onBack: () -> Unit,
    onContinue: () -> Unit,
    onStepClick: (Int) -> Unit,
    onLogin: () -> Unit
) {
    var selectedGender by remember { mutableStateOf<String?>(null) }
    var showError by remember { mutableStateOf(false) }

    Scaffold { paddingValues ->
        Surface(modifier = Modifier.fillMaxSize().padding(paddingValues),
            color= Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Back arrow
                BackArrowComponent(onBack)
                Spacer(modifier = Modifier.height(10.dp))

                // Illustration/Image
                 ImageComponent()
//                Spacer(modifier = Modifier.height(10.dp))

                // Create account text
                CreateAccountTextComponent()
                Spacer(modifier = Modifier.height(10.dp))

                // Progress bar
                SignUpProgressBarComponent(steps = listOf(
                    "Email",
                    "Name",
                    "Birthday",
                    "Gender",
                    "Pass"
                ),
                    currentStep = 3,
                    onStepClick = onStepClick)
                Spacer(modifier = Modifier.height(25.dp))

                // Gender dropdown
                GenderDropdownComponent(
                    selectedGender = selectedGender,
                    onGenderSelected = {
                        selectedGender = it
                        showError = false
                    },
                    showError = showError
                )
                Spacer(modifier = Modifier.height(32.dp))

                // Continue button
                 ContinueButtonComponent(enabled = selectedGender != null, showError = showError, onClick = {
                     if (selectedGender == null) {
                         showError = true
                     } else {
                         onContinue()
                     }
                 }, step = 3)
                Spacer(modifier = Modifier.height(24.dp))

                // Bottom text and divider
                BottomTextComponent(onLogin)
            }
        }
    }
}