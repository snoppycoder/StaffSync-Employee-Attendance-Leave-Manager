package com.example.figma_replicate.data.network

import com.example.figma_replicate.data.models.AttendanceRecord
import com.example.figma_replicate.data.models.AttendanceStats
import com.example.figma_replicate.data.models.CheckInResponse
import com.example.figma_replicate.data.models.CheckOutResponse
import com.example.figma_replicate.data.models.LeaveRequest
import com.example.figma_replicate.data.models.LeaveRequestResponse
import com.example.figma_replicate.data.models.LeaveStatsResponse
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

    @POST("api/attendance/check-in")
    suspend fun checkIn(): CheckInResponse

    @POST("api/attendance/check-out")
    suspend fun checkOut(): CheckOutResponse

    @GET("api/attendance")
    suspend fun getAttendance(@Query("userId") userId: Int? = null): List<AttendanceRecord>

    @GET("/api/leaveRequest/stats")
    suspend fun getLeaveStats(
        @Header("Authorization") token: String
    ): LeaveStatsResponse

    @GET("/api/leaveRequest")
    suspend fun getLeaveRequests(
        @Header("Authorization") token: String
    ): LeaveRequestResponse

    @GET("/api/leaveRequest/{id}")
    suspend fun fetchLeaveRequest(
        @Header("Authorization") token: String, @Path("id") id:Int?
    ): LeaveRequestResponse

    @POST("/api/leaveRequest")
    suspend fun applyLeave(
        @Header("Authorization") token: String,
        @Body leaveRequest: LeaveRequest
    ): LeaveRequest

    @PATCH("/api/users/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body user: User
    ): User








}