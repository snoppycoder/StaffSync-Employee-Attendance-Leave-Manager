package com.example.figma_replicate.domain.usecase


import com.example.figma_replicate.data.repository.AttendanceRepository
import com.example.figma_replicate.domain.model.Attendance
import com.example.figma_replicate.domain.model.Attendances
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MarkAttendanceUseCase(private val attendanceRepository: AttendanceRepository) {
    suspend operator fun invoke(
        userId: Int,
        date: String,
        checkIn: String? = null,
        checkOut: String? = null,
        status: Attendances? = null
    ): Result<String> {
        // Business logic: Validate input
        try {
            LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            checkIn?.let { LocalDateTime.parse(it, DateTimeFormatter.ISO_LOCAL_DATE_TIME) }
            checkOut?.let { LocalDateTime.parse(it, DateTimeFormatter.ISO_LOCAL_DATE_TIME) }
        } catch (e: Exception) {
            return Result.failure(Exception("Invalid date format"))
        }
        if (checkIn != null && checkOut != null) {
            val checkInTime = LocalDateTime.parse(checkIn, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            val checkOutTime = LocalDateTime.parse(checkOut, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            if (checkInTime.isAfter(checkOutTime)) {
                return Result.failure(Exception("Check-in time must be before check-out time"))
            }
        }
        // Create Attendance record and delegate to Repository
        val attendance = Attendance(
            userId = userId,
            date = date,
            checkIn = checkIn,
            checkOut = checkOut,
            attendance = status
        )
        return attendanceRepository.markAttendance(attendance)
    }
}