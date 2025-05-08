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

    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)
    var signedUpUser = mutableStateOf<User?>(null)

    fun signup(user: User) {
        isLoading.value = true
        errorMessage.value = null

        viewModelScope.launch {
            try {
                // Make the API call to signup
                signedUpUser.value = signupRepository.signup(user)
            } catch (e: Exception) {
                errorMessage.value = "Signup failed: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }
}
