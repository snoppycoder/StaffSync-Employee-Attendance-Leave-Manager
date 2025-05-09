package com.example.figma_replicate.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.models.UserRole
import com.example.figma_replicate.data.repository.SignupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    private var role: UserRole? = null
    private var fullName: String? = null
    private var email: String? = null
    private var gender: String? = null
    private var dob: String? = null
    private var password: String? = null
    private var username: String? = null
    private var designation: String? = null
    private var employmentType: String? = null
    fun setUsername(username: String) {
        println(username)
        this.username = username
    }
    fun setDesignation(designation: String) {

        this.designation = designation

    }
    fun setEmploymentType(employmentType: String) {
        this.employmentType = employmentType
    }

    fun setRole(role: UserRole) {
        this.role = role
    }

    fun setFullNameAndEmail(name: String, email: String) {
        this.fullName = name
        this.email = email
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun setDob(dob: String) {
        this.dob = dob
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun signup() {
//        if (!validateInput()) {
////            println(username, password, dob, designation, email, employmentType, fullName)
//            println("Incomplete or invalid input")
//            signupState.value = SignupState.Error("Incomplete or invalid input.")
//            return
//        }

        signupState.value = SignupState.Loading

        viewModelScope.launch {
            try {

//
                val user = signupRepository.signup(
                    User(
                        username = username,
                        gender = gender,
                        dateOfBirth = dob,
                        fullName = fullName,
                        password = password,
                        email = email,
                        role = role,
                        designation = designation,
                        employmentType = employmentType
                    )
                )
                println("user profile $user")

                signupState.value = SignupState.Success(user)
            } catch (e: Exception) {
                signupState.value = SignupState.Error("Signup failed. Please try again.")
                println("here is the error ${e.localizedMessage}, ${e.cause}")
//
            }
        }
    }

//    private fun validateInput(): Boolean {
//        return fullName?.isNotBlank() == true &&
//                email?.contains("@") == true &&
//                (password?.length ?: 0) >= 6 &&
//                role != null
//    }

    fun resetState() {
        signupState.value = SignupState.Idle
        role = null
        fullName = null
        email = null
        gender = null
        dob = null
        password = null
        username = null
        designation = null
        employmentType = null
    }
}