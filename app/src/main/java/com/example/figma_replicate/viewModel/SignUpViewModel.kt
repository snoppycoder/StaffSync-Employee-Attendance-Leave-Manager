package com.example.figma_replicate.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.models.UserRole
import com.example.figma_replicate.data.repository.SignupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.String

sealed class SignupState {
    object Idle : SignupState()
    object Loading : SignupState()
    data class Success(val user: User) : SignupState()
    data class Error(val message: String) : SignupState()
}

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupRepository: SignupRepository
) : ViewModel() {
    var signupState = mutableStateOf<SignupState>(SignupState.Idle)
        private set
    var username = mutableStateOf("")
        private set

    var fullName = mutableStateOf("")
        private set

    var email = mutableStateOf("")
        private set
    var confirmPassword = mutableStateOf("")
        private set

    var gender = mutableStateOf("")
        private set

    var dob = mutableStateOf("")
        private set

    var password = mutableStateOf("")
        private set

    var designation = mutableStateOf("")
        private set

    var employmentType = mutableStateOf("")
        private set

    var role = mutableStateOf<UserRole?>(null)
        private set


    fun setUsername(value: String) {
        username.value = value
    }

    fun setFullName(value: String) {
        fullName.value = value
    }

    fun setEmail(value: String) {
        email.value = value
    }

    fun setGender(value: String) {
        val upper_case = value.trim().uppercase()
        gender.value = upper_case
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setDob(value: String) {
        dob.value = value

    }

    fun setPassword(value: String) {
        password.value = value
    }

    fun setDesignation(value: String) {

        designation.value = value
    }

    fun setEmploymentType(value: String) {
        val upper_case = value.uppercase().trim()
        employmentType.value = upper_case
    }


    fun setRole(userRole: UserRole) {
        role.value = userRole
    }

    fun signup() {
        println("$username, $password, $dob, $designation, $email, $employmentType, $fullName, $role")

        signupState.value = SignupState.Loading

        viewModelScope.launch {
            try {

                val user = User(
                    username = username.value,
                    fullName = fullName.value,
                    email = email.value,
                    role = role.value,
                    gender = gender.value,
                    dateOfBirth = dob.value,
                    password = password.value,
                    designation = designation.value,
                    employmentType = employmentType.value
                )
                signupRepository.signup(user)
                println("here is the user $user")



                signupState.value = SignupState.Success(user)
            } catch (e: Exception) {
                signupState.value = SignupState.Error("Signup failed. Please try again.")
                println("here is the error ${e.localizedMessage}, ${e.cause}")

            }
        }
    }


    fun resetState() {
        signupState.value = SignupState.Idle
        username.value = ""
        fullName.value = ""
        email.value = ""
        gender.value = ""
        dob.value = ""
        password.value = ""
        designation.value = ""
        employmentType.value = ""
        role.value = null
    }
}