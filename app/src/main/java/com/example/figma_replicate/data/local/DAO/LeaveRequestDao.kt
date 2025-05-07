package com.example.figma_replicate.data.local.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.figma_replicate.data.local.entity.LeaveRequestEntity
import com.example.figma_replicate.domain.model.LeaveStatus

@Dao
interface LeaveRequestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeaveRequest(leaveRequest: LeaveRequestEntity)

    @Update
    suspend fun updateLeaveRequest(leaveRequest: LeaveRequestEntity)

    @Query("SELECT * FROM LeaveRequest WHERE userId = :userId")
    suspend fun getLeaveRequestsByUser(userId: Int): List<LeaveRequestEntity>

    @Query("SELECT * FROM LeaveRequest WHERE userId = :userId AND status = :status")
    suspend fun getLeaveRequestsByStatus(userId: Int, status: LeaveStatus): List<LeaveRequestEntity>

    @Query("SELECT * FROM LeaveRequest WHERE id = :id LIMIT 1")
    suspend fun getLeaveRequestById(id: Int): LeaveRequestEntity?

    @Query("DELETE FROM LeaveRequest WHERE id = :id")
    suspend fun deleteLeaveRequest(id: Int)
}