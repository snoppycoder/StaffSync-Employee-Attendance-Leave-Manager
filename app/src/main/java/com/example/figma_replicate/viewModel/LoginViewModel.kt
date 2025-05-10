package com.example.figma_replicate.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.models.LoginUsers
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
    data class Success(val user: User) : LoginState()
    data class Error(val message: String) : LoginState()
}
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel(){
    var loginState = mutableStateOf<LoginState>(LoginState.Idle)
        private set
    var username = mutableStateOf("")
        private set
    var password = mutableStateOf("")
        private set
    fun setUserName(value:String) {
        username.value = value
    }
    fun setPassword(value:String){
        password.value = value
    }
    fun login(){
        loginState.value = LoginState.Loading

        viewModelScope.launch {
            try {
                val user = loginRepository.login(
                    LoginUsers(
                        username=username.value,
                        password=password.value


                    )

                )
                loginState.value = LoginState.Success(user)


            }
            catch(e: Exception) {
                loginState.value = LoginState.Error("Login failed because of ${e.message}")
            }
        }

    }




}