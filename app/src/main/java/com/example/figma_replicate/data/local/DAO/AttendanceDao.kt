package com.example.figma_replicate.data.local.DAO


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.figma_replicate.data.local.entity.AttendanceEntity
import com.example.figma_replicate.domain.model.Attendances

@Dao
interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendance(attendance: AttendanceEntity)

    @Update
    suspend fun updateAttendance(attendance: AttendanceEntity)

    @Query("SELECT * FROM Attendance WHERE userId = :userId")
    suspend fun getAttendanceByUser(userId: Int): List<AttendanceEntity>

    @Query("SELECT * FROM Attendance WHERE userId = :userId AND date = :date LIMIT 1")
    suspend fun getAttendanceByUserAndDate(userId: Int, date: String): AttendanceEntity?

    @Query("SELECT * FROM Attendance WHERE userId = :userId AND attendance = :status")
    suspend fun getAttendanceByStatus(userId: Int, status: Attendances): List<AttendanceEntity>

    @Query("DELETE FROM Attendance WHERE id = :id")
    suspend fun deleteAttendance(id: Int)
}