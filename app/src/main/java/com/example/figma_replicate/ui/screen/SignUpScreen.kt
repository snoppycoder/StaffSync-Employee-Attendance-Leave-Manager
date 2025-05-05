package com.example.signup.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.signup.R
import com.example.signup.ui.theme.SignUpTheme

@Composable
fun SignUpScreen(
    onEmployeeClick: () -> Unit,
    onManagerClick: () -> Unit
) {
    val orange = Color(0xFFFF7F50)
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Image(
            painter = painterResource(id = R.drawable.signup_illustration),
            contentDescription = "Sign Up Illustration",
            modifier = Modifier
                .height(342.dp)
                .width(348.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Sign up as",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF36454F),
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        // Employee Button
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = orange,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(56.dp)
                .clickable {
                    onEmployeeClick()
                }
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "Employee",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
//        Button(onClick = onEmployeeClick) {
//            Text("Employee")
//        }
//        Button(onClick = onManagerClick) {
//            Text("Manager")
//        }

        Spacer(modifier = Modifier.height(20.dp))
        // Manager Button
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = orange,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(56.dp)
                .clickable {
                    onManagerClick()
                }
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "Manager",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
        // Bottom Text
        HorizontalDivider(modifier=Modifier.fillMaxWidth(0.85f), 2.dp, Color(0xFFD9D9D9)
        )
        Spacer(modifier=Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Have an account? ",
                fontSize = 14.sp,
                color = Color(0xFF36454F)
            )
            Text(
                text = "Log in",
                fontSize = 14.sp,
                color = orange,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    Toast.makeText(context, "TODO: Navigate to Log in", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun SignUpPreview() {
//
//}

