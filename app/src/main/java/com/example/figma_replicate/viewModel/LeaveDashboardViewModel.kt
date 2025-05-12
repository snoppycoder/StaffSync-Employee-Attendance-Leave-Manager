package com.example.figma_replicate.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.LeaveStatsResponse
import com.example.figma_replicate.data.repository.LeaveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LeaveState {
    object Idle : LeaveState()
    object Loading : LeaveState()
    data class Success(val stats: LeaveStatsResponse) : LeaveState()
    data class Error(val message: String) : LeaveState()
}

@HiltViewModel
class LeaveViewModel @Inject constructor(
    private val leaveRepository: LeaveRepository,
    private val authPrefs: AuthPrefs
) : ViewModel() {
    var leaveState = mutableStateOf<LeaveState>(LeaveState.Idle)
        private set

    init {
        Log.d("LeaveViewModel", "Initializing LeaveViewModel")
        fetchLeaveStats()
    }

    fun fetchLeaveStats() {
        Log.d("LeaveViewModel", "Fetching leave stats")
        leaveState.value = LeaveState.Loading
        viewModelScope.launch {
            try {
                val token = authPrefs.getToken()
                if (token == null) {
                    Log.e("LeaveViewModel", "No authentication token found")
                    leaveState.value = LeaveState.Error("No authentication token found")
                    return@launch
                }
                
                Log.d("LeaveViewModel", "Token found, calling repository")
                val result = leaveRepository.getLeaveStats(token)
                
                leaveState.value = when {
                    result.isSuccess -> {
                        val stats = result.getOrNull()
                        Log.d("LeaveViewModel", "Successfully fetched leave stats: $stats")
                        LeaveState.Success(stats!!)
                    }
                    else -> {
                        val error = result.exceptionOrNull()?.message ?: "Failed to fetch leave stats"
                        Log.e("LeaveViewModel", "Error fetching leave stats: $error")
                        LeaveState.Error(error)
                    }
                }
            } catch (e: Exception) {
                Log.e("LeaveViewModel", "Exception in fetchLeaveStats", e)
                leaveState.value = LeaveState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}