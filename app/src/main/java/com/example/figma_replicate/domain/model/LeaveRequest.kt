package com.example.figma_replicate.domain.model

data class LeaveRequest(
    val id: Int? = null, // Nullable, auto-incremented by server
    val userId: Int, // Foreign key to User
    val type: LeaveType, // Enum from schema
    val startDate: String,
    val endDate: String,
    val reason: String,
    val status: LeaveStatus = LeaveStatus.PENDING, // Default from schema
    val pointsDeduction: Int = 0, // Default from schema
    val approvedById: Int? = null, // Nullable foreign key to User
    val approvedAt: String? = null // Nullable, set when approved
)