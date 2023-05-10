package com.example.figma_replicate.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.figma_replicate.R
import com.example.figma_replicate.ui.component.BackArrowComponent
import com.example.figma_replicate.ui.component.OtpInputComponent
import com.example.figma_replicate.ui.component.RecoveryStepProgressBar
import kotlin.collections.joinToString

@Composable
fun OtpScreen(
    onBack: () -> Unit = {},
    onResend: () -> Unit,
    onVerifiedCode: (String) -> Unit = {}

) {
    var otpValue by remember { mutableStateOf(List(4) { "" }) }
    var isFocused by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        // Top title above back arrow
        Text(
            text = "Forgot Password",
            style = TextStyle(
                color = Color(0xFF000000),
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(resId = R.font.montserrat_medium))
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        BackArrowComponent(onBack = onBack)
        Spacer(modifier = Modifier.height(20.dp))
        // Main title
        Text(
            text = "Verify",
            style = TextStyle(
                color = Color(0xFF000000),
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(resId = R.font.montserrat_bold))
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        OtpInputComponent(
            otpValue = otpValue,
            onOtpChange = {otpValue = it}
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Didn’t Receive the Code ?",
            style = TextStyle(
                color = Color(0xFFC4C4C4),
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(resId = R.font.montserrat_medium))
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        ResendTextComponent(onResend = { onResend() })
        Spacer(modifier = Modifier.height(20.dp))
        RecoveryStepProgressBar(currentStep = 2)
        Spacer(modifier = Modifier.height(20.dp))
        RecoveryActionButton(
            buttonText = "Verify",
            enabled = true,
            onClick = {
                // TODO: Navigate to verify screen or handle receive code
                onVerifiedCode(otpValue.joinToString(""))
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OtpScreenPreview() {
    OtpScreen(onBack = {},
        onResend = {},
        onVerifiedCode = {})

}