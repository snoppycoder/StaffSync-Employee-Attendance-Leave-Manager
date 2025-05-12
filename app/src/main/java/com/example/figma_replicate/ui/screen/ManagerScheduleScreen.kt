package com.example.figma_replicate.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.figma_replicate.R

import com.example.figma_replicate.ui.component.ScheduleContent
import com.example.figma_replicate.ui.component.BottomNavBar
import com.example.figma_replicate.ui.component.Chronology
import com.example.figma_replicate.ui.component.LeaveTabs
import com.example.figma_replicate.ui.component.LeaveTeamLeave
import com.example.figma_replicate.ui.component.ListCardPast
import com.example.figma_replicate.ui.component.ListCardTeamLeave
import com.example.figma_replicate.ui.component.ListCardUpcoming
import com.example.figma_replicate.ui.component.ScheduleGrid

import com.example.figma_replicate.ui.component.ScheduleTopBar
@Composable
fun ManagerTeamLeave(){

    LeaveTeamLeave(
        "John Doe", "Lead UI/UX Developer",
        "Apr 15, 2025",
        "Apr 18 2025",
        "3"
    )

    Spacer(modifier = Modifier.height(8.dp))
    LeaveTeamLeave(
        "John Doe", "Lead UI/UX Developer",
        "Apr 15, 2025",
        "Apr 18 2025",
        "3"
    )
    Spacer(modifier = Modifier.height(8.dp))
    LeaveTeamLeave(
        "John Doe", "Lead UI/UX Developer",
        "Apr 15, 2025",
        "Apr 18 2025",
        "3"
    )


}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ManagerScheduleScreen(navController: NavController) {
    Scaffold(
        topBar = { ScheduleTopBar(navController) },

        ) { innerPadding ->


        val tabs = listOf("Upcoming", "Past", "Team Leave")
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())

        ) {
            ScheduleGrid()

            Spacer(modifier = Modifier.height(16.dp))

            LeaveTabs(tabs, selectedTabIndex) {
                selectedTabIndex = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (selectedTabIndex) {

                1 -> ListCardPast()
                2 -> ManagerTeamLeave()

            }
        }
    }
}