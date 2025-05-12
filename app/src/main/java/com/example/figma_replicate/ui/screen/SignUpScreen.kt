package com.example.figma_replicate.ui.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.figma_replicate.InputValidators.Designation_Validation
import com.example.figma_replicate.InputValidators.Email_Validator
import com.example.figma_replicate.InputValidators.FullName_Validator
import com.example.figma_replicate.InputValidators.Password_Validator
import com.example.figma_replicate.InputValidators.UserName_Validator
import com.example.figma_replicate.InputValidators.sigUpButtonEnabled

import com.example.figma_replicate.R
import com.example.figma_replicate.data.models.UserRole
import com.example.figma_replicate.ui.component.CalendarField
import com.example.figma_replicate.ui.component.StepIndicator
import com.example.figma_replicate.viewModel.SignupState
import com.example.figma_replicate.viewModel.SignupViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SignupScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.signupState
    when (state) {
        is SignupState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier.fillMaxSize()

            )
        }
        is SignupState.Error -> {
            LaunchedEffect(state) {
                Toast.makeText(context, (state as SignupState.Error).message, Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
        }
        is SignupState.Success -> {
            LaunchedEffect(state) {
                navController.navigate("home") {
                    popUpTo("signup") { inclusive = true }
                }
                viewModel.resetState()
            }
        }
        is SignupState.Idle -> {
            SignupForm(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun SignupForm(
    navController: NavController,
    viewModel: SignupViewModel
) {
    val orange = Color(0xFFFF7F50)

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
        Spacer(modifier = Modifier.height(50.dp))
        // Bottom Text
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(0.85f),
            thickness = 2.dp,
            color = Color(0xFFD9D9D9)
        )
        Row {
            Text(
                text = "No account?",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Login",
                color = orange,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
        }
    }
}

// Existing composables (CreateAccountFullName, CreateAccountGender, CreateAccountDOB, CreateAccountPassword, CreateAccountOTP) remain unchanged
@Composable
fun CreateAccountFullName(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal=16.dp)
            .verticalScroll(rememberScrollState())
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = R.drawable.signup_illustration),
            contentDescription = "Illustration",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        StepIndicator(0)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Create account", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        var username by viewModel.username
        OutlinedTextField(
            value = username,
            onValueChange = { viewModel.setUsername(it)},
            label = { Text("Enter Your Preferred Username") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        val userNameValidator:String? = UserName_Validator(username)
        if ( userNameValidator != null) {
            Text(
                userNameValidator, color=Color.Red
            )

        }



        Spacer(modifier = Modifier.height(16.dp))
        var name by  viewModel.fullName
        OutlinedTextField(
            value = name,
            onValueChange = { viewModel.setFullName(it) },
            label = { Text("Enter Your Full Name") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        val fullNameValidator:String? = FullName_Validator(name)
        if ( fullNameValidator != null) {
            Text(
                fullNameValidator, color=Color.Red
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        var email by viewModel.email
        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.setEmail(it) },
            label = { Text("Enter Your Email Address") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        val emailValidator:String? = Email_Validator(email)
        if ( emailValidator != null) {
            Text(
                emailValidator, color=Color.Red
            )

        }


        Spacer(modifier = Modifier.height(16.dp))
        var designation by  viewModel.designation
        OutlinedTextField(
            value = designation,
            onValueChange = { viewModel.setDesignation(it) },
            label = { Text("Enter Your Designation") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))
        val designation_validator:String? = Designation_Validation(designation)
        if (designation_validator != null) {
            Text(designation_validator, color=Color.Red)

        }

        Spacer(modifier = Modifier.height(8.dp))


        Button(
            onClick = {
                navController.navigate("gender")
            },
            enabled = fullNameValidator == null && emailValidator==null
                    && designation_validator==null && userNameValidator==null,
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
            .padding(horizontal=16.dp)
            .verticalScroll(rememberScrollState()),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() })
            {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = R.drawable.signup_illustration),
            contentDescription = "Illustration",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Create account", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        StepIndicator(1)
        Spacer(modifier = Modifier.height(16.dp))
        var employmentType by  viewModel.employmentType
        OutlinedTextField(
            value = employmentType,
            onValueChange = {viewModel.setEmploymentType(it) },
            label = { Text("Employment Type") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        var gender by viewModel.gender
        OutlinedTextField(
            value = gender,
            onValueChange = { viewModel.setGender(it) },
            label = { Text("Gender") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("dob")

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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateAccountDOB(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
) {
    var dob by viewModel.dob

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal=24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = R.drawable.signup_illustration),
            contentDescription = "Illustration",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Create account", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        StepIndicator(2)
        Spacer(modifier = Modifier.height(16.dp))

        CalendarField("Date of Birth", selectedDate = dob) { viewModel.setDob(it)}
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("password")

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
            .padding(horizontal=24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = R.drawable.signup_illustration),
            contentDescription = "Illustration",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Create account", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        StepIndicator(3)
        Spacer(modifier = Modifier.height(16.dp))
        var password by  viewModel.password
        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.setPassword(it)},
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        var confirm_password by remember { mutableStateOf("") }
        OutlinedTextField(
            value = confirm_password,
            onValueChange = { confirm_password = it },
            label = { Text("Confirm Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        val passwordValidator:String? = Password_Validator(password, confirm_password)
        if (passwordValidator != null) {
            Text(passwordValidator, color=Color.Red)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.signup()
                navController.navigate("login")



            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),

            colors = ButtonDefaults.buttonColors(Color(0xFFFF7043)),
            enabled = passwordValidator == null
        ) {
            Text("Confirm", color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
