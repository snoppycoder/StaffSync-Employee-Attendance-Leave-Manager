package com.example.figma_replicate.data.repository

import com.example.figma_replicate.data.models.CheckInResponse
import com.example.figma_replicate.data.models.CheckOutResponse
import com.example.figma_replicate.data.models.AttendanceRecord
import com.example.figma_replicate.data.models.AttendanceStats
import com.example.figma_replicate.data.network.ApiServiceInterface
import javax.inject.Inject

class AttendanceRepository @Inject constructor(
    private val apiService: ApiServiceInterface
) {
    suspend fun checkIn(): Result<CheckInResponse> {
        return try {
            Result.success(apiService.checkIn())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun checkOut(): Result<CheckOutResponse> {
        return try {
            Result.success(apiService.checkOut())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAttendance(userId: Int? = null): Result<List<AttendanceRecord>> {
        return try {
            Result.success(apiService.getAttendance(userId))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}