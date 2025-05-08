package com.example.figma_replicate.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack



import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.figma_replicate.R

@Composable
fun SignUpScreen(
    onEmployeeClick: () -> Unit,
    onManagerClick: () -> Unit, navController: NavController
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
        Spacer(modifier = Modifier.height(80.dp))
        // Bottom Text
        HorizontalDivider(modifier=Modifier.fillMaxWidth(0.85f), 2.dp, Color(0xFFD9D9D9)
        )
//        Spacer(modifier=Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
                   navController.navigate("login")
                }
            )
        }

    }
}
@Composable
fun CreateAccountFullName(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back arrow
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {
                navController.popBackStack()

            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Illustration
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your vector or image
            contentDescription = "Illustration",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text("Create account", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Step Indicator
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val steps = listOf("Email", "Name", "Birthday", "Gender", "Pass")
            steps.forEachIndexed { index, label ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = if (index == 0) painterResource(id=R.drawable.ic_ok) else painterResource(id=R.drawable.ic_circle) ,
                        contentDescription = label,
                        tint = if (index == 0) Color(0xFFFF7043) else Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = label, fontSize = 10.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Input Field
        var name by remember { mutableStateOf("") }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Your Full Name") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Continue Button
        Button(
            onClick = {
                navController.navigate("gender")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Continue", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Footer Text
        Row {
            Text("Have an account? ", color = Color.Gray)
            Text(
                text = "Log in",
                color = Color(0xFFFF7043),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { /* Navigate to login */ }
            )
        }
    }
}

@Composable
fun CreateAccountGender(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back arrow
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {
                navController.popBackStack()

            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Illustration
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your vector or image
            contentDescription = "Illustration",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text("Create account", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Step Indicator
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val steps = listOf("Email", "Name", "Birthday", "Gender", "Pass")
            steps.forEachIndexed { index, label ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = if (index == 0) painterResource(id=R.drawable.ic_ok) else painterResource(id=R.drawable.ic_circle) ,
                        contentDescription = label,
                        tint = if (index == 0) Color(0xFFFF7043) else Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = label, fontSize = 10.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Input Field
        var name by remember { mutableStateOf("") }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Your Full Name") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Continue Button
        Button(
            onClick = { /* Navigate to next step */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Next", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Footer Text
        Row {
            Text("Have an account? ", color = Color.Gray)
            Text(
                text = "Log in",
                color = Color(0xFFFF7043),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { /* Navigate to login */ }
            )
        }
    }
}
