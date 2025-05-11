package com.example.figma_replicate.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.CheckInResponse
import com.example.figma_replicate.data.models.CheckOutResponse
import com.example.figma_replicate.data.models.AttendanceRecord
import com.example.figma_replicate.data.models.AttendanceStats
import com.example.figma_replicate.data.repository.AttendanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AttendanceState {
    object Idle : AttendanceState()
    object Loading : AttendanceState()
    data class Success(val data: Any) : AttendanceState()
    data class Error(val message: String) : AttendanceState()
}

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val attendanceRepository: AttendanceRepository,
    private val authPrefs: AuthPrefs
) : ViewModel() {
    var attendanceState = mutableStateOf<AttendanceState>(AttendanceState.Idle)
        private set

    fun checkIn() {
        attendanceState.value = AttendanceState.Loading
        viewModelScope.launch {
            val result = attendanceRepository.checkIn()
            attendanceState.value = when {
                result.isSuccess -> AttendanceState.Success(result.getOrNull()!!)
                else -> AttendanceState.Error(result.exceptionOrNull()?.message ?: "Check-in failed")
            }
        }
    }

    fun checkOut() {
        attendanceState.value = AttendanceState.Loading
        viewModelScope.launch {
            val result = attendanceRepository.checkOut()
            attendanceState.value = when {
                result.isSuccess -> AttendanceState.Success(result.getOrNull()!!)
                else -> AttendanceState.Error(result.exceptionOrNull()?.message ?: "Check-out failed")
            }
        }
    }

    fun getAttendance(userId: Int? = null) {
        attendanceState.value = AttendanceState.Loading
        viewModelScope.launch {
            val result = attendanceRepository.getAttendance(userId)
            attendanceState.value = when {
                result.isSuccess -> AttendanceState.Success(result.getOrNull()!!)
                else -> AttendanceState.Error(result.exceptionOrNull()?.message ?: "Failed to fetch attendance")
            }
        }
    }

    fun getAttendanceStats(startDate: String? = null, endDate: String? = null) {
        if (!authPrefs.isManager()) {
            attendanceState.value = AttendanceState.Error("Only managers can view attendance stats")
            return
        }
        attendanceState.value = AttendanceState.Loading
        viewModelScope.launch {
            val result = attendanceRepository.getAttendanceStats(startDate, endDate)
            attendanceState.value = when {
                result.isSuccess -> AttendanceState.Success(result.getOrNull()!!)
                else -> AttendanceState.Error(result.exceptionOrNull()?.message ?: "Failed to fetch stats")
            }
        }
    }

    fun resetState() {
        attendanceState.value = AttendanceState.Idle
    }

    fun isManager(): Boolean {
        return authPrefs.isManager()
    }
}