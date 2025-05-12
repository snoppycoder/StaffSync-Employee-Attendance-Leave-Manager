package com.example.figma_replicate.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.figma_replicate.viewModel.LeaveViewModel

@Composable
fun LeaveDashboardContent(innerPadding: PaddingValues) {
    val viewModel: LeaveViewModel = hiltViewModel()
    val tabs = listOf("Upcoming", "Past", "Team Leave")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        LeaveStatGrid(viewModel = viewModel)

        Spacer(modifier = Modifier.height(16.dp))

        LeaveTabs(tabs, selectedTabIndex) {
            selectedTabIndex = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        when(selectedTabIndex) {
            0 -> ListCardUpcoming()
            1 -> ListCardPast()
            2 -> ListCardTeamLeave()
        }
    }
}
