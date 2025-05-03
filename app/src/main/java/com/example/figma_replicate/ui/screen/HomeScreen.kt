package com.example.figma_replicate.ui.screen
import ListOfActivity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.figma_replicate.navigation.Routes
import com.example.figma_replicate.ui.component.*

 @Composable
@RequiresApi(Build.VERSION_CODES.O)
fun HomeScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Routes.NOTIFICATION && currentRoute != Routes.APPLY_LEAVE) {
                BottomNavBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME,
            modifier = Modifier
        ) {
            composable(Routes.HOME) {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(vertical = 16.dp)
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
//                            .background(Color(0xFFF2F2F2))
                    ) {
                        Attendance()
                        ListOfActivity()
                    }
                }
            }

            composable(Routes.SCHEDULE) {
                ScheduleScreen(navController)
            }
            composable(Routes.OFFICE) {
                // OfficeScreen()
            }
            composable(Routes.HOLIDAY) {
                // HolidayScreen()
            }
            composable(Routes.PROFILE) {
                ProfileScreen(navController=navController)
            }
            composable(Routes.NOTIFICATION) {
                NotificationScreen(navController = navController)
            }
            composable(Routes.APPLY_LEAVE) {
                LeaveFormScreen(navController)
            }
        }
    }
}