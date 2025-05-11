package com.example.figma_replicate.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.LoginRequest
import com.example.figma_replicate.data.models.LoginResponse
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.models.UserRole
import com.example.figma_replicate.data.repository.LoginRepository
import com.example.figma_replicate.data.repository.SignupRepository
import com.example.figma_replicate.ui.screen.LoginScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.String
sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val loginRequest: LoginRequest) : LoginState()
    data class Error(val message: String) : LoginState()
}
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val authPrefs: AuthPrefs
): ViewModel(){
    var loginState = mutableStateOf<LoginState>(LoginState.Idle)
        private set
    var username = mutableStateOf("")
        private set
    var password = mutableStateOf("")
        private set
    fun setUserName(value:String) {
        val trimmed = value.trim()
        username.value = trimmed
    }
    fun setPassword(value:String){
        password.value = value
    }
    fun login() {
        loginState.value = LoginState.Loading
        viewModelScope.launch {
            println("${username.value} & ${password.value}")

            try {
                println("here $username")


                val user = loginRepository.login(
                    LoginRequest(
                        username = username.value.toString(),
                        password = password.value.toString()
                    )
                )
                loginState.value = LoginState.Success(
                    loginRequest = LoginRequest(username = username.value, password = password.value)

                )

                authPrefs.saveAuthData(
                    token = user.token,
                    userId = user.id.toString(),
                    username = user.username,
                    role = user.role
                )
                println("here ${username.value}")
            } catch (e: Exception) {
                loginState.value = LoginState.Error("Login failed because of ${e.message}")
                println("here $e")
            }
        }
    }

    private fun saveAuthPreferences() {
        authPrefs.saveAuthData(
            token = authPrefs.getToken(), // Preserve existing token
            username = authPrefs.getUsername() ?: "",
            role = authPrefs.getUserRole() ?: UserRole.EMPLOYEE,
            userId = authPrefs.getUserId() ?: ""
        )
    }




}