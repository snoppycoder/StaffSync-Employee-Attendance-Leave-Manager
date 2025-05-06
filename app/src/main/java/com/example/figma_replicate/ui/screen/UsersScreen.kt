package com.example.figma_replicate.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.figma_replicate.viewModel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(viewModel: UserViewModel = viewModel()) {
    val users by viewModel.users.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = { Text("Users") })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
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
                    Column(modifier = Modifier.padding(16.dp)) {
                        users.forEach { user ->
                            Text(
                                text = "${user.username} - ${user.email}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Divider(modifier = Modifier.padding(vertical = 4.dp))
                        }
                    }
                }
            }
        }
    }
}
