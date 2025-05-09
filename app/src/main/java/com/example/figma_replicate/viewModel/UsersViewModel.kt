package com.example.figma_replicate.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figma_replicate.data.models.User
import com.example.figma_replicate.data.network.ApiServiceInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
//        fetchUsers()
    }

//    private fun fetchUsers() {
//        viewModelScope.launch {
//            _isLoading.value = true
//            try {
//                val userList = ApiService.retrofitService.getUsers()
//                _users.value = userList
//                _errorMessage.value = null
//            } catch (e: Exception) {
//                Log.e("UserViewModel", "Failed to fetch users", e)
//                _errorMessage.value = "Failed to load users: ${e.localizedMessage}"
//            } finally {
//                _isLoading.value = false
//            }
//        }
//    }
}
