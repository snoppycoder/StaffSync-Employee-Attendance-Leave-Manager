package com.example.figma_replicate.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.LoginRequest
import com.example.figma_replicate.data.models.LoginResponse
import com.example.figma_replicate.data.models.UserRole
import com.example.figma_replicate.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: LoginResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val authPrefs: AuthPrefs
) : ViewModel() {
    var loginState = mutableStateOf<LoginState>(LoginState.Idle)
        private set
    var username = mutableStateOf("")
        private set
    var password = mutableStateOf("")
        private set
    var rememberMe = mutableStateOf(false)
        private set
    var loginMethod = mutableStateOf("email")
        private set

    init {
        loadAuthPreferences()
    }

    fun setUserName(value: String) {
        username.value = value
    }

    fun setPassword(value: String) {
        password.value = value
    }

    fun setRememberMe(value: Boolean) {
        rememberMe.value = value
        saveAuthPreferences()
    }

    fun setLoginMethod(value: String) {
        loginMethod.value = value
        saveAuthPreferences()
    }

    fun login() {
        loginState.value = LoginState.Loading
        viewModelScope.launch {
            try {
                val user = loginRepository.login(
                    LoginRequest(
                        username = username.value,
                        password = password.value
                    )
                )
                loginState.value = LoginState.Success(user)
                authPrefs.saveAuthData(
                    token = user.token,
                    userId = user.id.toString(),
                    username = user.username,
                    email = user.email,
                    role = user.role
                )
            } catch (e: Exception) {
                loginState.value = LoginState.Error("Login failed: ${e.message}")
            }
        }
    }

    fun resetState() {
        loginState.value = LoginState.Idle
    }

    private fun saveAuthPreferences() {
        authPrefs.saveAuthData(
            token = authPrefs.getToken(),
            userId = authPrefs.getUserId() ?: "",
            username = authPrefs.getUsername() ?: "",
            email = authPrefs.getEmail() ?: "",
            role = authPrefs.getUserRole() ?: UserRole.EMPLOYEE
        )
    }

    private fun loadAuthPreferences() {
        if (authPrefs.isAuthenticated() ) {
            username.value = authPrefs.getUsername() ?: ""
        }
    }

    fun clearAuthPreferences() {
        authPrefs.clearAuthData()
        username.value = ""
        password.value = ""
        rememberMe.value = false
        loginMethod.value = "email"
    }

    fun isAuthenticated(): Boolean {
        return authPrefs.isAuthenticated()
    }

    fun isManager(): Boolean {
        return authPrefs.isManager()
    }
}