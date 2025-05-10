package com.example.figma_replicate.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.figma_replicate.R
import com.example.figma_replicate.data.models.UserRole
import com.example.figma_replicate.viewModel.LoginState
import com.example.figma_replicate.viewModel.LoginViewModel
import androidx.compose.ui.platform.LocalContext

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    navController: NavController
) {
    val orange = Color(0xFFFF7F50)
    val context = LocalContext.current
    var username by viewModel.username
    var password by viewModel.password
    val loginState by viewModel.loginState

    when (loginState) {
        is LoginState.Idle -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your asset
                    contentDescription = "Login Illustration",
                    modifier = Modifier
                        .height(280.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Back to sync?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Access your account by logging in",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(24.dp))
                // Username Field
                OutlinedTextField(
                    value = username,
                    onValueChange = { viewModel.setUserName(it) },
                    label = { Text("Username") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_key), // Replace with your email icon
                            contentDescription = "Username"
                        )
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Password Field
                OutlinedTextField(
                    value = password,
                    onValueChange = { viewModel.setPassword(it) },
                    label = { Text("Password") },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_key), // Replace with your lock icon
                            contentDescription = "Password"
                        )
                    },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Forgot Password?",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
                            onForgotPasswordClick()
                            navController.navigate("forgotpassword")
                        }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        viewModel.login()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = orange)
                ) {
                    Text(
                        text = "Log in",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Text(
                        text = "No account?",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Sign up",
                        color = orange,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.clickable {
                            onSignUpClick()
                            navController.navigate("signup")
                        }
                    )
                }
            }
        }
        is LoginState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        is LoginState.Success -> {
            LaunchedEffect(loginState) {
                val role = (loginState as LoginState.Success).user.role
                val destination = when (role) {
                    UserRole.MANAGER -> "schedule"
                    UserRole.EMPLOYEE -> "profile"
                }
                onLoginClick()
                navController.navigate(destination) {
                    popUpTo("login") { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
        is LoginState.Error -> {
            LaunchedEffect(loginState) {
                val error = (loginState as LoginState.Error).message
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()

            }
        }
    }
}

