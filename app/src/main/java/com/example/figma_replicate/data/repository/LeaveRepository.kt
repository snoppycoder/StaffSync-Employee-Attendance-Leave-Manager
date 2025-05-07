package com.example.figma_replicate.data.repository

import com.example.figma_replicate.data.local.db.AppDatabase
import com.example.figma_replicate.data.local.entity.LeaveRequestEntity
import com.example.figma_replicate.data.network.ApiService
import com.example.figma_replicate.domain.model.LeaveRequest
import com.example.figma_replicate.domain.model.LeaveStatus

class LeaveRepository(
    private val apiService: ApiService,
    private val db: AppDatabase
) {
    suspend fun requestLeave(leaveRequest: LeaveRequest): Result<String> {
        return try {
            val response = apiService.requestLeave(leaveRequest)
            if (response.isSuccessful) {
                val updatedRequest = response.body() ?: return Result.failure(Exception("No response body"))
                val entity = LeaveRequestEntity(
                    id = updatedRequest.id,
                    userId = updatedRequest.userId,
                    type = updatedRequest.type,
                    startDate = updatedRequest.startDate,
                    endDate = updatedRequest.endDate,
                    reason = updatedRequest.reason,
                    status = updatedRequest.status,
                    pointsDeduction = updatedRequest.pointsDeduction,
                    approvedById = updatedRequest.approvedById,
                    approvedAt = updatedRequest.approvedAt
                )
                db.leaveRequestDao().insertLeaveRequest(entity)
                Result.success("Leave request submitted successfully")
            } else {
                Result.failure(Exception("Failed to submit leave request: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun approveLeave(
        leaveRequestId: Int,
        managerId: Int,
        status: LeaveStatus,
        pointsDeduction: Int
    ): Result<String> {
        return try {
            val response = apiService.approveLeave(mapOf(
                "leaveRequestId" to leaveRequestId,
                "managerId" to managerId,
                "status" to status.name,
                "pointsDeduction" to pointsDeduction
            ))
            if (response.isSuccessful) {
                val updatedRequest = db.leaveRequestDao().getLeaveRequestById(leaveRequestId)
                    ?: return Result.failure(Exception("Leave request not found"))
                val updatedEntity = updatedRequest.copy(
                    status = status,
                    approvedById = managerId,
                    pointsDeduction = pointsDeduction,
                    approvedAt = LocalDateTime.now().toString()
                )
                db.leaveRequestDao().updateLeaveRequest(updatedEntity)
                Result.success("Leave request updated successfully")
            } else {
                Result.failure(Exception("Failed to update leave request: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}