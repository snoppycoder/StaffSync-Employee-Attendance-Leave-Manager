package com.example.figma_replicate.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import com.example.figma_replicate.R
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.figma_replicate.data.models.User

import com.example.figma_replicate.viewModel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun UsersScreen(viewModel: UsersViewModel = hiltViewModel()) {
    val users by viewModel.users.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Employees") })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ,

            contentAlignment = Alignment.Center
        ) {
            when {
                loading -> {
                    CircularProgressIndicator()
                }
                error != null -> {
                    Text(
                        text = error ?: "Unknown error",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                users.isEmpty() -> {
                    Text(
                        text = "No users found.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                else -> {
                    LazyColumn(modifier = Modifier.padding(16.dp)) {
                        items(users) { user ->
                            EmployeeItem(employee = user)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EmployeeItem(
    employee: User // Assuming 'User' is the data class
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
//            painter = rememberAsyncImagePainter(employee.photoUrl),
            painter = painterResource(id=R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)
            .padding(16.dp))

            {
            Text(
                text = employee.fullName.toString()?: "Unknown User",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = employee.role.toString(),
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Text(
//            text = employee.status,
            text="Checked In",
//            color = if (employee.status == "Checked In") Color(0xFF4CAF50) else Color.Red,
                    color = Color(0xFF4CAF50),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }

    Divider(color = Color.LightGray, thickness = 0.5.dp)
}

