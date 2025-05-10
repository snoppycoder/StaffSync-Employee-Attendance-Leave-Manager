package com.example.figma_replicate.data.network

import com.example.figma_replicate.data.models.LoginRequest
import com.example.figma_replicate.data.models.LoginResponse
import com.example.figma_replicate.data.models.SignupRequest
import com.example.figma_replicate.data.models.SignupResponse
import com.example.figma_replicate.data.models.User
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServiceInterface {
    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("api/signup")
    suspend fun signup(@Body user: User): User
}