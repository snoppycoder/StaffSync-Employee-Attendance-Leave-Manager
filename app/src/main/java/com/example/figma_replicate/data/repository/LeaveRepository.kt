package com.example.figma_replicate.data.repository

import android.util.Log
import com.example.figma_replicate.data.AuthPrefs
import com.example.figma_replicate.data.models.LeaveRequest
import com.example.figma_replicate.data.models.LeaveRequestResponse
import com.example.figma_replicate.data.models.LeaveStatsResponse
import com.example.figma_replicate.data.network.ApiServiceInterface
import javax.inject.Inject

class LeaveRepository @Inject constructor(
    private val apiService: ApiServiceInterface,
    private val authPrefs: AuthPrefs,
) {
    suspend fun getLeaveStats(token: String): Result<LeaveStatsResponse> {
        return try {
            Log.d("LeaveRepository", "Fetching leave stats with token: ${token.take(10)}...")
            // Ensure token is properly formatted with Bearer prefix
            val authToken = if (token.startsWith("Bearer ")) token else "Bearer $token"
            val response = apiService.getLeaveStats(authToken)
            Log.d("LeaveRepository", "Successfully fetched leave stats: $response")
            Result.success(response)
        } catch (e: Exception) {
            Log.e("LeaveRepository", "Error fetching leave stats", e)
            Result.failure(e)
        }
    }
    suspend fun applyLeave(leaveRequest: LeaveRequest): Result<LeaveRequest> {
        return try {
            val token = authPrefs.getToken() ?: return Result.failure(Exception("No token found"))
            val response = apiService.applyLeave("Bearer $token", leaveRequest)
            if (true) {
                Result.success(response)
            } else {
                Result.failure(Exception("Empty response"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchLeaveRequest(): LeaveRequestResponse {
        return apiService.fetchLeaveRequest("Bearer ${authPrefs.getToken()}")
    }
}