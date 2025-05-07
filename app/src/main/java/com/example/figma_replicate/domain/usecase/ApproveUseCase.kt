package com.example.figma_replicate.domain.usecase

import com.example.figma_replicate.data.repository.LeaveRepository
import com.example.figma_replicate.domain.model.LeaveStatus

class ApproveLeaveUseCase(private val leaveRepository: LeaveRepository) {
    suspend operator fun invoke(
        leaveRequestId: Int,
        managerId: Int,
        status: LeaveStatus,
        pointsDeduction: Int
    ): Result<String> {
        // Business logic: Validate input
        if (status != LeaveStatus.APPROVED && status != LeaveStatus.REJECTED) {
            return Result.failure(Exception("Status must be APPROVED or REJECTED"))
        }
        if (status == LeaveStatus.APPROVED && pointsDeduction <= 0) {
            return Result.failure(Exception("Points deduction must be greater than 0 for approved leaves"))
        }
        // Delegate to Repository
        return leaveRepository.approveLeave(leaveRequestId, managerId, status, pointsDeduction)
    }
}