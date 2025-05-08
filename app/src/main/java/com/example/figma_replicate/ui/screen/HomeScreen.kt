package com.example.figma_replicate.ui.screen

import ListOfActivity
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.figma_replicate.navigation.Routes
import com.example.figma_replicate.ui.component.*
import com.example.figma_replicate.ui.screen.LoginScreen

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun HomeScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Routes.NOTIFICATION && currentRoute != Routes.APPLY_LEAVE
                && currentRoute != Routes.NOTIFICATION_SETTING
                && currentRoute != Routes.CHANGE_PASSWORD && Routes.SIGNUP != currentRoute && Routes.LOGIN != currentRoute
                && currentRoute != Routes.PASSWORD && currentRoute != Routes.OTP && currentRoute != Routes.FULLNAME &&
                currentRoute != Routes.DOB && currentRoute != Routes.GENDER
                )
            {
                BottomNavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SIGNUP,
            modifier = Modifier
        ) {
            composable(Routes.HOME) {
                Column(
                    modifier = Modifier

                        .padding(innerPadding)
                        .padding(vertical = 16.dp)
//                        .verticalScroll(rememberScrollState())


                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ){
                        ProfileView(navController = navController)
                        DaysOfTheWeek()
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()

//
                    ) {
                        AttendanceGrid()
                        ListOfActivity()
                    }
                }
            }

            composable(Routes.SCHEDULE) {
                ScheduleScreen(navController)
            }
            composable(Routes.OFFICE) {
                UsersScreen()
                // OfficeScreen()
            }
            composable(Routes.HOLIDAY) {
                // HolidayScreen()
            }
            composable(Routes.PROFILE) {
                Column(

                )

                {
                    Spacer(modifier=Modifier.height(24.dp))
                    ProfileScreen(navController = navController)
                }
            }
            composable(Routes.NOTIFICATION) {
                NotificationScreen(navController = navController)
            }
            composable(Routes.APPLY_LEAVE) {
                LeaveFormScreen(navController=navController)
            }
            composable( Routes.EDIT_PROFILE)  {
                 EditProfileScreen(navController=navController)
            }
            composable( Routes.SIGNUP)  {
                val context = LocalContext.current

                SignUpScreen(onEmployeeClick = {

                    navController.navigate("fullname")
                 },
                     onManagerClick = {
                         navController.navigate("fullname")
                     }, navController=navController
                 )
            }
            composable(Routes.NOTIFICATION_SETTING){
                NotificationSettingScreen(navController = navController)
            }
            composable (Routes.CHANGE_PASSWORD) {
                ChangePasswordScreen(navController=navController)
            }
            composable (Routes.FORGOT_PASSWORD) {
                ForgotPassword(navController=navController)
            }
            composable (Routes.FULLNAME) {
                CreateAccountFullName(navController=navController)
            }
            composable (Routes.GENDER) {
                CreateAccountGender(navController=navController)
            }
            composable (Routes.DOB) {
                CreateAccountDOB(navController=navController)
            }
            composable (Routes.PASSWORD) {
                CreateAccountPassword(navController=navController)
            }
            composable (Routes.OTP) {
                CreateAccountOTP(navController=navController)
            }
            composable (Routes.LOGIN) {
                val context = LocalContext.current
                LoginScreen(
                    onLoginClick = {  Toast.makeText(context, "Employee Clicked", Toast.LENGTH_SHORT).show()},
                onSignUpClick={Toast.makeText(context, "Employee Clicked", Toast.LENGTH_SHORT).show()},
                onForgotPasswordClick={  Toast.makeText(context, "Employee Clicked", Toast.LENGTH_SHORT).show()},
                navController=navController
                )
            }


        }
    }
}