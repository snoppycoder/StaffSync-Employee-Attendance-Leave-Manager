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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.figma_replicate.R

import com.example.figma_replicate.ui.component.ScheduleContent
import com.example.figma_replicate.ui.component.BottomNavBar
import com.example.figma_replicate.ui.component.Chronology
import com.example.figma_replicate.ui.component.LeaveStatGrid
import com.example.figma_replicate.ui.component.LeaveTabs
import com.example.figma_replicate.ui.component.LeaveTeamLeave
import com.example.figma_replicate.ui.component.ListCardPast
import com.example.figma_replicate.ui.component.ListCardTeamLeave
import com.example.figma_replicate.ui.component.ListCardUpcoming
import com.example.figma_replicate.ui.component.ManagerTopBar
import com.example.figma_replicate.ui.component.ScheduleGrid

import com.example.figma_replicate.ui.component.ScheduleTopBar
import com.example.figma_replicate.viewModel.LeaveFormViewModel
import com.example.figma_replicate.viewModel.LeaveViewModel

@Composable
fun ManagerTeamLeave(){




}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ManagerScheduleScreen(navController: NavController) {
    val viewModel: LeaveViewModel = hiltViewModel()
    Scaffold(
        topBar = { ManagerTopBar(navController) },

        ) { innerPadding ->


        val tabs = listOf("Past", "Team Leave")
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())

        ) {
            LeaveStatGrid(viewModel = viewModel)

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