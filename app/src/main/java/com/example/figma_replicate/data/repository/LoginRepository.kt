package com.example.figma_replicate.data.repository

import com.example.figma_replicate.data.models.LoginRequest
import com.example.figma_replicate.data.models.LoginResponse
import com.example.figma_replicate.data.network.ApiServiceInterface
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiServiceInterface
) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return apiService.login(loginRequest)
    }
}