package com.example.figma_replicate.ui.screen

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.figma_replicate.ui.component.BackArrowComponent
import com.example.figma_replicate.ui.component.BottomTextComponent
import com.example.figma_replicate.ui.component.ContinueButtonComponent
import com.example.figma_replicate.ui.component.CreateAccountTextComponent
import com.example.figma_replicate.ui.component.DateOfBirthField
import com.example.figma_replicate.ui.component.ImageComponent
import com.example.figma_replicate.ui.component.SignUpProgressBarComponent
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BirthDayScreen(
    onBack: () -> Unit,
    onContinue: (String) -> Unit,
    onStepClick: (Int) -> Unit,
    onLogin: () -> Unit
){
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var dateOfBirth by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    fun isValidBirthday(input: String): Boolean {
        return try {
            val date = LocalDate.parse(input, dateFormatter)
            val today = LocalDate.now()
            val age = Period.between(date, today).years
            age >= 18 && !date.isAfter(today)
        } catch (e: DateTimeParseException) {
            false
        }
    }

    Column(
            modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(30.dp))
        // Top Bar
        BackArrowComponent(onBack)
        Spacer(modifier = Modifier.height(4.dp))
        // Illustration
        ImageComponent()
        Spacer(modifier = Modifier.height(14.dp))
        // Title
        CreateAccountTextComponent()
        Spacer(modifier = Modifier.height(16.dp))
        // Progress Indicator
        SignUpProgressBarComponent(
            steps = listOf("Email", "Name", "Birthday", "Gender", "Pass"),
            currentStep = 2,
            onStepClick = onStepClick
        )
        // Date of Birth Field
        Spacer(modifier = Modifier.height(20.dp))
        DateOfBirthField(
            onDateChanged = { dateString ->
                dateOfBirth = dateString
                showError = false // Reset error when editing
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        ContinueButtonComponent(
            enabled = isValidBirthday(dateOfBirth),
            showError = showError,
            onClick = {
                if (isValidBirthday(dateOfBirth)) {
                    onContinue(dateOfBirth)
                } else {
                    showError = true
                }
            },
            step = 2
        )
        Spacer(modifier = Modifier.height(24.dp))
//        Divider and Bottom Text
        BottomTextComponent(onLogin)
    }
}
