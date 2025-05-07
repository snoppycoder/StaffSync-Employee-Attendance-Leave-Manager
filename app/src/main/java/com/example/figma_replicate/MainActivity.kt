package com.example.figma_replicate

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.figma_replicate.data.local.db.AppDatabase
import com.example.figma_replicate.data.network.RetrofitClient
import com.example.figma_replicate.data.repository.AuthRepository
import com.example.figma_replicate.ui.screen.HomeScreen
import com.example.figma_replicate.ui.theme.Figma_replicateTheme
import androidx.annotation.RequiresApi

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var appDatabase: AppDatabase
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Room database
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "leave_attendance_db"
        ).build()

        // Initialize AuthRepository with Room and Retrofit
        val authRepository = AuthRepository(
            apiService = RetrofitClient.apiService,
            context = this,
            db = appDatabase
        )

        setContent {
            Figma_replicateTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    HomeScreen()
                }
            }
        }
    }
}
