package com.example.figma_replicate.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.repository.SignupRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignupViewModel @Inject constructor(
    private val signupRepository: SignupRepository
) : ViewModel() {

    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)
    val signedUpUser = mutableStateOf<User?>(null)

    fun signup(user: User) {
        isLoading.value = true
        errorMessage.value = null

        viewModelScope.launch {
            try {
                val response = signupRepository.signup(user)
                signedUpUser.value = response
            } catch (e: Exception) {
                errorMessage.value = "Signup failed: ${e.localizedMessage ?: "Unknown error"}"
            } finally {
                isLoading.value = false
            }
        }
    }
}
