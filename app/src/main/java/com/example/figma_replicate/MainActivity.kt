package com.example.figma_replicate

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.UserRole
//import com.example.figma_replicate.SessionManagement.SessionViewModel
import com.example.figma_replicate.navigation.Routes
import com.example.figma_replicate.ui.component.*
import com.example.figma_replicate.ui.screen.*
import com.example.figma_replicate.ui.theme.Figma_replicateTheme
import com.example.figma_replicate.viewModel.HolidayViewModel
import com.example.figma_replicate.viewModel.LoginViewModel
import com.example.figma_replicate.viewModel.SignupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Figma_replicateTheme {
//                val sessionViewModel: SessionViewModel = hiltViewModel()
                MainScreen()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen (

) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current
    val authPrefs = remember { AuthPrefs(context) }


    Scaffold(
        bottomBar = {
            if (currentRoute != Routes.NOTIFICATION && currentRoute != Routes.APPLY_LEAVE &&
                currentRoute != Routes.NOTIFICATION_SETTING && currentRoute != Routes.CHANGE_PASSWORD &&
                currentRoute != Routes.SIGNUP && currentRoute != Routes.LOGIN &&
                currentRoute != Routes.FULLNAME && currentRoute != Routes.DOB &&
                currentRoute != Routes.PASSWORD && currentRoute != Routes.GENDER &&
                currentRoute != Routes.FORGOT_PASSWORD

            ) {
                BottomNavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "signup_flow",
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(startDestination = Routes.SIGNUP, route = "signup_flow") {
                composable(Routes.SIGNUP) {
                    val parentEntry = remember { navController.getBackStackEntry("signup_flow") }
                    val viewModel: SignupViewModel = hiltViewModel(parentEntry)
                    SignupScreen(navController = navController, viewModel)
                }

                composable(Routes.FULLNAME) { backStackEntry ->
                    val parentEntry = remember { navController.getBackStackEntry("signup_flow") }
                    val viewModel: SignupViewModel = hiltViewModel(parentEntry)
                    CreateAccountFullName(navController, viewModel)
                }

                composable(Routes.GENDER) { backStackEntry ->
                    val parentEntry = remember { navController.getBackStackEntry("signup_flow") }
                    val viewModel: SignupViewModel = hiltViewModel(parentEntry)
                    CreateAccountGender(navController, viewModel)
                }

                composable(Routes.DOB) { backStackEntry ->
                    val parentEntry = remember { navController.getBackStackEntry("signup_flow") }
                    val viewModel: SignupViewModel = hiltViewModel(parentEntry)
                    CreateAccountDOB(navController, viewModel)
                }

                composable(Routes.PASSWORD) { backStackEntry ->
                    val parentEntry = remember { navController.getBackStackEntry("signup_flow") }
                    val viewModel: SignupViewModel = hiltViewModel(parentEntry)
                    CreateAccountPassword(navController, viewModel)
                }
            }
            navigation(startDestination = Routes.HOME,  route = "navigation_bar"){


                composable(Routes.HOME) {

                    HomeScreen(
                        navController = navController,
                        authPrefs = AuthPrefs(context = LocalContext.current)


                    )
                }
                composable(Routes.SCHEDULE) {
                    val updatedUserRole = remember { authPrefs.getUserRole() }
                    if (updatedUserRole == UserRole.EMPLOYEE) {
                        ScheduleScreen(navController = navController)

                    }
                    else{
                        ManagerScheduleScreen(navController)
                    }




                }
                composable(Routes.OFFICE) {
                    val updatedUserRole = remember { authPrefs.getUserRole() }
                    if (updatedUserRole == UserRole.EMPLOYEE) {
                        UsersScreen()
                    } else {
                        ManagerScreen()
                    }

                }

                composable(Routes.HOLIDAY) {
                    // Get the HolidayViewModel using hiltViewModel
                    val holidayViewModel: HolidayViewModel = hiltViewModel()
                    val authPrefs = remember { AuthPrefs(context) }


                    HolidayScreen(
                        viewModel = holidayViewModel,
                        authPrefs = authPrefs
                    )
                }
                composable(Routes.PROFILE) {
                    ProfileScreen(navController = navController)
                }

            }

            composable(Routes.NOTIFICATION) {
                NotificationScreen(navController = navController)
            }
            composable(Routes.APPLY_LEAVE) {
                LeaveFormScreen(navController = navController)
            }
            composable(Routes.EDIT_PROFILE) {
                EditProfileScreen(navController = navController)
            }

            composable(Routes.NOTIFICATION_SETTING) {
                NotificationSettingScreen(navController = navController)
            }
            composable(Routes.CHANGE_PASSWORD) {
                ChangePasswordScreen(navController = navController)
            }
            composable(Routes.FORGOT_PASSWORD) {
                ForgotPassword(navController = navController)
            }

            composable(Routes.LOGIN) {
                val context = LocalContext.current
                val userRole = remember { authPrefs.getUserRole() }
                val viewModel: LoginViewModel = hiltViewModel()
                LoginScreen(

                    onLoginClick = {

                        navController.navigate(Routes.HOME) },
                    onSignUpClick = {
                        Toast.makeText(context, "Employee Clicked", Toast.LENGTH_SHORT).show()
                    },
                    onForgotPasswordClick = {
                        Toast.makeText(context, "Employee Clicked", Toast.LENGTH_SHORT).show()
                    },
                    navController = navController
                )
            }
        }
    }
}