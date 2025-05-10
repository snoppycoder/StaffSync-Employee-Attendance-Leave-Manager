package com.example.figma_replicate.data.network

import com.example.figma_replicate.data.models.LoginRequest
import com.example.figma_replicate.data.models.LoginResponse
import com.example.figma_replicate.data.models.User
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServiceInterface {
    @Headers("Content-Type: application/json")
    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("api/signup")
    suspend fun signup(@Body user: User): User
}