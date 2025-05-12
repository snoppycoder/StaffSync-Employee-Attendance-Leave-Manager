package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LeaveStatsResponse(
    val leaveBalance: Int,
    val leaveApproved: Int,
    val leavePending: Int,
    val leaveCancelled: Int
)

@Serializable
data class LeaveRequestResponse(
    val id: Int,
    val type: String,
    val startDate: String,
    val endDate: String,
    val approvedById: Int,
    val approvedByName: String,
    val status: String,
    val reason: String,
    val leaveBalance: Int,
    val applyDays: Int

)

