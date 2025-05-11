package com.example.figma_replicate.data.repository

import android.util.Log
import com.example.figma_replicate.data.models.LeaveStatsResponse
import com.example.figma_replicate.data.network.ApiServiceInterface
import javax.inject.Inject

class LeaveRepository @Inject constructor(
    private val apiService: ApiServiceInterface
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
}