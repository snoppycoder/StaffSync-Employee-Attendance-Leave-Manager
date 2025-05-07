package com.example.figma_replicate.domain.usecase

import com.example.figma_replicate.data.repository.AttendanceRepository
import com.example.figma_replicate.domain.model.Attendance

class GetUserAttendanceUseCase(private val attendanceRepository: AttendanceRepository) {
    suspend operator fun invoke(userId: Int): Result<List<Attendance>> {
        // Business logic: Validate input
        if (userId <= 0) {
            return Result.failure(Exception("Invalid user ID"))
        }
        // Delegate to Repository
        return attendanceRepository.getAttendanceByUser(userId)
    }
}