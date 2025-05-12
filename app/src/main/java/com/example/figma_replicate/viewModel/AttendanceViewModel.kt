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
    data class Success(val data: List<AttendanceRecord>) : AttendanceState()
    data class Error(val message: String) : AttendanceState()
}

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val attendanceRepository: AttendanceRepository,
    private val authPrefs: AuthPrefs
) : ViewModel() {
    var attendanceState = mutableStateOf<AttendanceState>(AttendanceState.Idle)
        private set

    init {
        getAttendance() // Load records on initialization
    }

    fun checkIn() {
        attendanceState.value = AttendanceState.Loading
        viewModelScope.launch {
            val result = attendanceRepository.checkIn()
            if (result.isSuccess) {
                getAttendance() // Refresh attendance list
            } else {
                attendanceState.value = AttendanceState.Error(result.exceptionOrNull()?.message ?: "Check-in failed")
            }
        }
    }

    fun checkOut() {
        attendanceState.value = AttendanceState.Loading
        viewModelScope.launch {
            val result = attendanceRepository.checkOut()
            if (result.isSuccess) {
                getAttendance() // Refresh attendance list
            } else {
                attendanceState.value = AttendanceState.Error(result.exceptionOrNull()?.message ?: "Check-out failed")
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



    fun resetState() {
        attendanceState.value = AttendanceState.Idle
    }

//    fun isManager(): Boolean {
//        return authPrefs.isManager()
//    }
}