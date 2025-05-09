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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.figma_replicate.R
import com.example.figma_replicate.data.models.UserRole
import com.example.figma_replicate.viewModel.SignupState
import com.example.figma_replicate.viewModel.SignupViewModel


@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignupViewModel = viewModel ()
) {
    val orange = Color(0xFFFF7F50)
    val context = LocalContext.current

    // Observe ViewModel state
    when (val state = viewModel.signupState.value) {
        is SignupState.Loading -> {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        is SignupState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            viewModel.resetState() // Reset error state after showing
        }
        is SignupState.Success -> {
            navController.navigate("home") {
                popUpTo("signup") { inclusive = true }
            }
            viewModel.resetState() // Reset state after navigation
        }
        is SignupState.Idle -> Unit
    }

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
                    viewModel.setRole(UserRole.EMPLOYEE)
                    navController.navigate("fullname")
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
        Spacer(modifier = Modifier.height(20.dp))
        // Manager Button
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = orange,
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .height(56.dp)
                .clickable {
                    viewModel.setRole(UserRole.MANAGER)
                    navController.navigate("fullname")
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
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
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
            val steps = listOf("Email", "Gender", "Birthday", "Password", "OTP")
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
        var username by remember { mutableStateOf("") }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Enter Your Preferred Username") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Input Field
        var name by remember { mutableStateOf("") }
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Your Full Name") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        var email by remember { mutableStateOf("") }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter Your Email Address") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Input Field
        var designation by remember { mutableStateOf("") }
        OutlinedTextField(
            value = designation,
            onValueChange = { designation = it },
            label = { Text("Enter Your Designation") },
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
                viewModel.setFullNameAndEmail(name, email)
                viewModel.setDesignation(designation)
                viewModel.setUsername(username)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Continue", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}
@Composable
fun CreateAccountGender(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
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


        Text("Create account", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val steps = listOf("Email", "Gender", "Birthday", "Password", "OTP")


            steps.forEachIndexed { index, label ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = if (index == 1) painterResource(id=R.drawable.ic_ok) else painterResource(id=R.drawable.ic_circle) ,
                        contentDescription = label,
                        tint = if (index == 1) Color(0xFFFF7043) else Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = label, fontSize = 10.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Input Field
        var employmentType by remember { mutableStateOf("") }
        OutlinedTextField(
            value = employmentType,
            onValueChange = { employmentType = it },
            label = { Text("Employment Type") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
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
            onClick = {
                navController.navigate("dob")
                viewModel.setGender(gender)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Continue", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}

@Composable
fun CreateAccountDOB(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
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

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your vector or image
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
            val steps = listOf("Email", "Gender", "Birthday", "Password", "OTP")
            steps.forEachIndexed { index, label ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = if (index == 2) painterResource(id=R.drawable.ic_ok) else painterResource(id=R.drawable.ic_circle) ,
                        contentDescription = label,
                        tint = if (index == 2) Color(0xFFFF7043) else Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = label, fontSize = 10.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Input Field
        var dob by remember { mutableStateOf("") }
        OutlinedTextField(
            value = dob,
            onValueChange = { dob = it },
            label = { Text("Date of Birth") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))


        Button(
            onClick = {
                navController.navigate("password")
                viewModel.setDob(dob)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Continue", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}
@Composable
fun CreateAccountPassword(
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
            val steps = listOf("Email", "Gender", "Birthday", "Password", "OTP")
            steps.forEachIndexed { index, label ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = if (index == 3) painterResource(id=R.drawable.ic_ok) else painterResource(id=R.drawable.ic_circle) ,
                        contentDescription = label,
                        tint = if (index == 3) Color(0xFFFF7043) else Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = label, fontSize = 10.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Input Field
        var password by remember { mutableStateOf("") }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        var confirm_password by remember { mutableStateOf("") }
        OutlinedTextField(
            value = confirm_password,
            onValueChange = { confirm_password = it },
            label = { Text("Confirm Password") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Continue Button
        Button(
            onClick = {
                navController.navigate("otp")
                viewModel.setPassword(password)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Continue", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))


    }
}
@Composable
fun CreateAccountOTP(navController: NavController,
                     viewModel: SignupViewModel = hiltViewModel()
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
            val steps = listOf("Email", "Gender", "Birthday", "Password", "OTP")
            steps.forEachIndexed { index, label ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = if (index == 4) painterResource(id = R.drawable.ic_ok) else painterResource(
                            id = R.drawable.ic_circle
                        ),
                        contentDescription = label,
                        tint = if (index == 4) Color(0xFFFF7043) else Color.LightGray,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(text = label, fontSize = 10.sp, color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        val otpLength = 5
        val otpValues = remember { MutableList(otpLength) { mutableStateOf("") } }
        val focusRequesters = List(otpLength) { FocusRequester() }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Instruction
            Text("Enter the 6-digit code we sent to", fontSize = 14.sp, color = Color.Black)
            Text("joe@gmail.com", fontSize = 14.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            // Resend
            Text(
                "Resend code",
                fontSize = 14.sp,
                color = Color(0xFFFF7043),
                modifier = Modifier.clickable { }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // OTP Fields
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                otpValues.forEachIndexed { index, value ->
                    OutlinedTextField(
                        value = value.value,
                        onValueChange = {
                            if (it.length <= 1 && it.all(Char::isDigit)) {
                                value.value = it
                                if (it.isNotEmpty() && index < otpLength - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        singleLine = true,
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        ),
                        modifier = Modifier
                            .size(50.dp)
                            .focusRequester(focusRequesters[index])
                            .focusProperties {
                                next =
                                    if (index < otpLength - 1) focusRequesters[index + 1] else FocusRequester.Default
                                previous =
                                    if (index > 0) focusRequesters[index - 1] else FocusRequester.Default
                            },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Confirm Button
            Button(
                onClick = {
                    navController.navigate("login")
                    viewModel.signup()


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF7043))
            ) {
                Text("Confirm", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}


