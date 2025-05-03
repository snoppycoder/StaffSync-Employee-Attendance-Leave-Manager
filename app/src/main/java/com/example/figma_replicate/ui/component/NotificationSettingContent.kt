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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.figma_replicate.navigation.Routes
import com.example.figma_replicate.ui.component.LeaveCardList
import com.example.figma_replicate.ui.component.LeaveTabs
import com.example.figma_replicate.ui.component.ScheduleGrid
@Composable
fun NotificationSettingContent(innerPadding: PaddingValues) {
    var isChecked by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(innerPadding)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Aligns content left and right
        ) {
            Column {
                Text(text = "Notifications", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge, color=MaterialTheme.colorScheme.primary)
                Text(text = "Turn notification on or off", style = MaterialTheme.typography.bodySmall)
            }

            Switch(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    uncheckedThumbColor = Color.White,
                    checkedTrackColor = MaterialTheme.colorScheme.primary,
                    uncheckedTrackColor = Color.Black

                ),

                modifier = Modifier.scale(0.75f)
            )
        }

        val options = listOf(
            "Only my activities" to "Only be notified of your activity with managers.",
            "Only others activities" to "Only be notified of others activities with managers.",
            "All activities" to "Be notified of all the activities."
        )

        var expanded by remember { mutableStateOf(false) }
        var selectedIndex by remember { mutableStateOf(0) }

        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()


            ){
                Column(
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    }



                ){
                    Text(
                        text = "Get notified from",
                        color = Color(0xFFFF5722),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Choose the activities you like to be notified about",
                        style = MaterialTheme.typography.bodySmall,

                    )
                }
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                options.forEachIndexed { index, pair ->
                    DropdownMenuItem(
                        text = {
                            Row(modifier =Modifier.fillMaxWidth()){
                                Column {
                                    Text(
                                        text = pair.first,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFFF5722)

                                    )
                                    Text(
                                        text = pair.second,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                RadioButton(
                                    selected = index == selectedIndex,
                                    onClick = {
                                        selectedIndex = index
                                        expanded = false
                                    }
                                )
                            }

                        },
                        onClick = {
                            selectedIndex = index
                            expanded = false

                        }

                    )
                }
            }
        }
    }


}
