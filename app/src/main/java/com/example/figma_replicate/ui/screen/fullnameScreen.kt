package com.example.figma_replicate.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview
import com.example.figma_replicate.R

@Composable
fun FullNameScreen(
    onBack: () -> Unit,
    onContinue: (String) -> Unit,
    onStepClick: (Int) -> Unit,
    onLogin: () -> Unit
) {
    val orange = Color(0xFFFF7F50)
    val gray = Color(0xFFD9D9D9)
    val darkGray = Color(0xFF36454F)
    val context = LocalContext.current

    var fullName by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }


    fun isValidName(name: String): Boolean {
        return name.isNotBlank() && name.all { it.isLetter() || it.isWhitespace() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = darkGray,
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onBack() }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.signup_illustration),
            contentDescription = "Sign Up Illustration",
            modifier = Modifier
                .height(342.dp)
                .width(348.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Create account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF36454F)
        )
        Spacer(modifier = Modifier.height(16.dp))

        SignUpProgressBar(
            currentStep = 1,
            onStepClick = onStepClick,
            orange = orange,
            gray = gray
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Your Full Name",
            fontSize = 18.sp,
            color = darkGray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = {
                fullName = it
                showError = false
            },
            placeholder = { Text("John doe", color = gray) },
            singleLine = true,
            isError = showError,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            textStyle = TextStyle(fontSize = 18.sp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (isValidName(fullName)) {
                        onContinue(fullName)
                    } else {
                        showError = true
                    }
                }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = orange,
                unfocusedBorderColor = gray,
                errorBorderColor = Color.Red
            )
        )
        if (showError) {
            Text(
                text = "Enter valid name",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.Start).padding(top = 4.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (isValidName(fullName)) {
                    onContinue(fullName)
                } else {
                    showError = true
                }
            },
            enabled = isValidName(fullName),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange,
                disabledContainerColor = gray
            )
        ) {
            Text(
                text = "Continue",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        HorizontalDivider(
            color = gray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Have an account? ",
                fontSize = 14.sp,
                color = darkGray
            )
            Text(
                text = "Log in",
                fontSize = 14.sp,
                color = orange,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    Toast.makeText(context, "TODO: Navigate to Log in", Toast.LENGTH_SHORT).show()
                    onLogin()
                }
            )
        }
    }
}

@Composable
fun SignUpProgressBar(
    currentStep: Int,
    onStepClick: (Int) -> Unit,
    orange: Color,
    gray: Color
) {
    val steps = listOf("Email", "Name", "Birthday", "gender", "pass")
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        steps.forEachIndexed { index, label ->
            val isCompleted = index < currentStep
            val isCurrent = index == currentStep
            val circleColor = when {
                isCompleted -> orange
                isCurrent -> Color.White
                else -> Color.DarkGray
            }
            val borderColor = when {
                isCompleted -> orange
                isCurrent -> orange
                else -> gray
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(circleColor)
                    .border(2.dp, borderColor, CircleShape)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        if (isCompleted) onStepClick(index)
                    },
                contentAlignment = Alignment.Center
            ) {
                if (isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text(
                        text = label.first().uppercase(),
                        color = borderColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
            if (index < steps.lastIndex) {
                Spacer(
                    modifier = Modifier
                        .width(16.dp)
                        .height(2.dp)
                        .background(if (index < currentStep) orange else gray)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FullNamePreview() {
    FullNameScreen(
        onBack = { /* Do nothing for preview */ },
        onContinue = { /* Do nothing for preview */ },
        onStepClick = { /* Do nothing for preview */ },
        onLogin = { /* Do nothing for preview */ }
    )
}