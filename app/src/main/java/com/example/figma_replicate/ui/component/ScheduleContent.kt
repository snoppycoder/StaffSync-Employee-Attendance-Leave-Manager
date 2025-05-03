package com.example.figma_replicate.ui.componen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.figma_replicate.navigation.Routes
import com.example.figma_replicate.ui.component.LeaveCardList
import com.example.figma_replicate.ui.component.LeaveTabs
import com.example.figma_replicate.ui.component.ScheduleGrid

@Composable
fun ScheduleContent(innerPadding: PaddingValues) {
    val tabs = listOf("Upcoming", "Past", "Team Leave")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        ScheduleGrid()
        Spacer(modifier = Modifier.height(16.dp))

        LeaveTabs(tabs, selectedTabIndex) { selectedTabIndex = it }

        Spacer(modifier = Modifier.height(16.dp))

        LeaveCardList()
    }
}
