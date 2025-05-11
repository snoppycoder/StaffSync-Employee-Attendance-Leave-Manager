package com.example.figma_replicate.data.network

import com.example.figma_replicate.data.models.LoginRequest
import com.example.figma_replicate.data.models.LoginResponse
import com.example.figma_replicate.data.models.User
import retrofit2.http.*

interface ApiServiceInterface {
    @Headers("Content-Type: application/json")
    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("api/signup")
    suspend fun signup(@Body user: User): User
    @GET("/api/users/employee")
    suspend fun fetchEmployee(@Header("Authorization") token:String): List<User>
    @GET("/api/users/manager")
    suspend fun fetchManager(@Header("Authorization") token:String): List<User>
    @GET("/api/users/{id}")
    suspend fun fetchInfo(@Path("id") id: Int?): User






}