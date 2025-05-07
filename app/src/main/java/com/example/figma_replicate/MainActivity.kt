package com.example.figma_replicate

//import android.os.Bundle
//import androidx.activity.ComponentActivity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.figma_replicate.ui.screen.BirthDayScreen
import com.example.figma_replicate.ui.screen.GenderScreen
import com.example.figma_replicate.ui.screen.HomeScreen
import com.example.figma_replicate.ui.screen.PasswordScreen
import com.example.figma_replicate.ui.screen.SignUpScreen
import com.example.figma_replicate.ui.theme.Figma_replicateTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Figma_replicateTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    HomeScreen()
                    AppNavHost()
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "signup") {
        composable("signup") {
            SignUpScreen(
                onEmployeeClick = { navController.navigate("fullname") },
                onManagerClick = { navController.navigate("fullname") }
            )
        }
        composable("fullname") {
            FullNameScreen(
                onBack = { navController.popBackStack() },
                onContinue = { navController.navigate("birthday") },
                onStepClick = { /* handle step click */ },
                onLogin = { /* handle login */ }
            )
        }

        composable("birthday") {
            BirthDayScreen(
                onBack = { navController.popBackStack() },
                onContinue = { navController.navigate("gender")},
                onStepClick = { /* handle step click */ },
                onLogin = { /* handle login */ }
            )
        }
        composable("gender"){
            GenderScreen(
                onBack = {navController.popBackStack()},
                onContinue = {navController.navigate("password")},
                onStepClick = {},
                onLogin = {}
            )
        }
        composable("password") {
            PasswordScreen(
                onBack = {navController.popBackStack()},
                onFinish = {},
                onStepClick = {},
                onLogin = {}
            )
        }
    }
}

@Composable
fun FullNameScreen(
    onBack: () -> Boolean,
    onContinue: () -> Unit,
    onStepClick: () -> Unit,
    onLogin: () -> Unit
) {
    TODO("Not yet implemented")
}

