package com.example.figma_replicate.data.repository

import com.example.figma_replicate.data.models.SignupRequest
import com.example.figma_replicate.data.models.SignupResponse
import com.example.figma_replicate.data.network.ApiServiceInterface
import javax.inject.Inject

class SignupRepository @Inject constructor(
    private val apiService: ApiServiceInterface
) {
    suspend fun signup(signupRequest: SignupRequest): SignupResponse {
        return apiService.signup(signupRequest)
    }
}