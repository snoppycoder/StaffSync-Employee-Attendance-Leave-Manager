package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LeaveStatsResponse(
    val leaveBalance: Int,
    val leaveApproved: Int,
    val leavePending: Int,
    val leaveCancelled: Int
)