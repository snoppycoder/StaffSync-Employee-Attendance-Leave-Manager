package com.example.figma_replicate.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.figma_replicate.R
import com.example.figma_replicate.viewModel.SignupViewModel

@Composable
fun SignUpScreen(
    onEmployeeClick: () -> Unit,
    onManagerClick: () -> Unit,
    navController: NavController
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

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = orange,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(56.dp)
                .clickable { onEmployeeClick() }
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Employee", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Surface(
            shape = RoundedCornerShape(12.dp),
            color = orange,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(56.dp)
                .clickable { onManagerClick() }
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text("Manager", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(80.dp))

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(0.85f),
            thickness = 2.dp,
            color = Color(0xFFD9D9D9)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Have an account? ", fontSize = 14.sp, color = Color(0xFF36454F))
            Text(
                text = "Log in",
                fontSize = 14.sp,
                color = orange,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { navController.navigate("login") }
            )
        }
    }
}

@Composable
fun CreateAccountFullName(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Illustration",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Create account", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val steps = listOf("Email", "Name", "Birthday", "Gender", "Pass")
            steps.forEachIndexed { index, label ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = if (index == 1) painterResource(id = R.drawable.ic_ok) else painterResource(id = R.drawable.ic_circle),
                        contentDescription = label,
                        tint = if (index == 1) Color(0xFFFF7043) else Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(label, fontSize = 10.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

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

        Button(
            onClick = { navController.navigate("gender") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Continue", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text("Have an account? ", color = Color.Gray)
            Text("Log in", color = Color(0xFFFF7043), fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun CreateAccountGender(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Illustration",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Create account", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val steps = listOf("Email", "Name", "Birthday", "Gender", "Pass")
            steps.forEachIndexed { index, label ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = if (index == 3) painterResource(id = R.drawable.ic_ok) else painterResource(id = R.drawable.ic_circle),
                        contentDescription = label,
                        tint = if (index == 3) Color(0xFFFF7043) else Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(label, fontSize = 10.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        var gender by remember { mutableStateOf("") }
        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

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

        Row {
            Text("Have an account? ", color = Color.Gray)
            Text("Log in", color = Color(0xFFFF7043), fontWeight = FontWeight.SemiBold)
        }
    }
}
