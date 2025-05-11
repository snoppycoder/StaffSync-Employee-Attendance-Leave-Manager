package com.example.figma_replicate.data.network


import com.example.figma_replicate.data.models.AttendanceRecord
import com.example.figma_replicate.data.models.AttendanceStats
import com.example.figma_replicate.data.models.CheckInResponse
import com.example.figma_replicate.data.models.CheckOutResponse
import com.example.figma_replicate.data.models.LoginRequest
import com.example.figma_replicate.data.models.LoginResponse
import com.example.figma_replicate.data.models.SignupRequest
import com.example.figma_replicate.data.models.SignupResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServiceInterface {
    @POST("/api/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("/api/signup")
    suspend fun signup(@Body signupRequest: SignupRequest): SignupResponse



        @POST("api/attendance/check-in")
        suspend fun checkIn(): CheckInResponse

        @POST("api/attendance/check-out")
        suspend fun checkOut(): CheckOutResponse

        @GET("api/attendance")
        suspend fun getAttendance(@Query("userId") userId: Int? = null): List<AttendanceRecord>

        @GET("api/attendance/stats")
        suspend fun getAttendanceStats(
            @Query("startDate") startDate: String? = null,
            @Query("endDate") endDate: String? = null
        ): AttendanceStats
    }
