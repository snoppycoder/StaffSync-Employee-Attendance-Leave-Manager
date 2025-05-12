package com.example.figma_replicate.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.models.LeaveRequest
import com.example.figma_replicate.data.repository.LeaveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaveFormViewModel @Inject constructor(
    private val leaveRepository: LeaveRepository
) : ViewModel() {

    // Use mutableStateOf for Compose UI state
    var leaveType by mutableStateOf("")
        private set
    var startDate by mutableStateOf("")
        private set
    var endDate by mutableStateOf("")
        private set
    var reason by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow<LeaveFormUiState>(LeaveFormUiState.Idle)
    val uiState: StateFlow<LeaveFormUiState> = _uiState.asStateFlow()


    fun updateLeaveType(newLeaveType: String) {
        leaveType = newLeaveType
    }

    fun updateStartDate(newStartDate: String) {
        startDate = newStartDate
    }

    fun updateEndDate(newEndDate: String) {
        endDate = newEndDate
    }

    fun updateReason(newReason: String) {
        reason = newReason
    }

    fun submitLeaveRequest() {
        // Basic validation
        if ( leaveType.isBlank() || startDate.isBlank() || endDate.isBlank()) {
            _uiState.value = LeaveFormUiState.Error("Please fill all required fields")
            return
        }

        val request = LeaveRequest(
            type = leaveType,
            startDate = startDate,
            endDate = endDate,
            reason = reason
        )

        viewModelScope.launch {
            _uiState.value = LeaveFormUiState.Loading
            val result = leaveRepository.applyLeave(request)
            _uiState.value = result.fold(
                onSuccess = { LeaveFormUiState.Success },
                onFailure = { LeaveFormUiState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    fun clearUiState() {
        _uiState.value = LeaveFormUiState.Idle
    }
}

sealed class LeaveFormUiState {
    object Idle : LeaveFormUiState()
    object Loading : LeaveFormUiState()
    object Success : LeaveFormUiState()
    data class Error(val message: String) : LeaveFormUiState()
}