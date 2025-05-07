package com.example.figma_replicate.domain.usecase


import com.example.figma_replicate.data.repository.LeaveRepository
import com.example.figma_replicate.domain.model.LeaveRequest
import com.example.figma_replicate.domain.model.LeaveType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RequestLeaveUseCase(private val leaveRepository: LeaveRepository) {
    suspend operator fun invoke(
        userId: Int,
        type: LeaveType,
        startDate: String,
        endDate: String,
        reason: String
    ): Result<String> {
        // Business logic: Validate input
        if (reason.isBlank()) {
            return Result.failure(Exception("Reason cannot be empty"))
        }
        try {
            val formatter = DateTimeFormatter.ISO_LOCAL_DATE
            val start = LocalDate.parse(startDate, formatter)
            val end = LocalDate.parse(endDate, formatter)
            if (start.isAfter(end)) {
                return Result.failure(Exception("Start date must be before end date"))
            }
            if (start.isBefore(LocalDate.now())) {
                return Result.failure(Exception("Start date cannot be in the past"))
            }
        } catch (e: Exception) {
            return Result.failure(Exception("Invalid date format"))
        }
        // Create LeaveRequest and delegate to Repository
        val leaveRequest = LeaveRequest(
            userId = userId,
            type = type,
            startDate = startDate,
            endDate = endDate,
            reason = reason
        )
        return leaveRepository.requestLeave(leaveRequest)
    }
}