package com.example.figma_replicate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.figma_replicate.domain.model.LeaveStatus
import com.example.figma_replicate.domain.model.LeaveType

@Entity(tableName = "LeaveRequest")
data class LeaveRequestEntity(
    @PrimaryKey val id: Int? = null,
    val userId: Int,
    val type: LeaveType,
    val startDate: String,
    val endDate: String,
    val reason: String,
    val status: LeaveStatus = LeaveStatus.PENDING,
    val pointsDeduction: Int = 0,
    val approvedById: Int? = null,
    val approvedAt: String? = null
)