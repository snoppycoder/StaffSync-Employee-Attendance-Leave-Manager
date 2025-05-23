package com.example.figma_replicate.viewModel

import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.models.UserRole
import com.example.figma_replicate.data.repository.EmployeeRepository
import com.example.figma_replicate.data.repository.LoginRepository
import com.example.figma_replicate.data.repository.SignupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.String


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,): ViewModel()

{

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val userList = employeeRepository.fetchEmployee()
                _users.value = userList
                _errorMessage.value = null
            } catch (e: Exception) {
                Log.e("UserViewModel", "Failed to fetch users", e)
                _errorMessage.value = "Failed to load users: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}