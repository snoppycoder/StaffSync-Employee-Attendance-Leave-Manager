package com.example.figma_replicate.data.repository


import com.example.figma_replicate.data.local.db.AppDatabase
import com.example.figma_replicate.data.local.entity.AttendanceEntity
import com.example.figma_replicate.data.network.ApiService
import com.example.figma_replicate.domain.model.Attendance

class AttendanceRepository(
    private val apiService: ApiService,
    private val db: AppDatabase
) {
    suspend fun markAttendance(attendance: Attendance): Result<String> {
        return try {
            val response = apiService.markAttendance(attendance)
            if (response.isSuccessful) {
                val updatedAttendance = response.body() ?: return Result.failure(Exception("No response body"))
                val entity = AttendanceEntity(
                    id = updatedAttendance.id,
                    userId = updatedAttendance.userId,
                    date = updatedAttendance.date,
                    checkIn = updatedAttendance.checkIn,
                    checkOut = updatedAttendance.checkOut,
                    attendance = updatedAttendance.attendance
                )
                db.attendanceDao().insertAttendance(entity)
                Result.success("Attendance marked successfully")
            } else {
                Result.failure(Exception("Failed to mark attendance: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAttendanceByUser(userId: Int): Result<List<Attendance>> {
        return try {
            val response = apiService.getAttendanceByUser(userId)
            if (response.isSuccessful) {
                val remoteAttendance = response.body() ?: emptyList()
                remoteAttendance.forEach { attendance ->
                    val entity = AttendanceEntity(
                        id = attendance.id,
                        userId = attendance.userId,
                        date = attendance.date,
                        checkIn = attendance.checkIn,
                        checkOut = attendance.checkOut,
                        attendance = attendance.attendance
                    )
                    db.attendanceDao().insertAttendance(entity)
                }
                Result.success(remoteAttendance)
            } else {
                // Fallback to local data if network fails
                val localAttendance = db.attendanceDao().getAttendanceByUser(userId)
                    .map { Attendance(it.id, it.userId, it.date, it.checkIn, it.checkOut, it.attendance) }
                Result.success(localAttendance)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}