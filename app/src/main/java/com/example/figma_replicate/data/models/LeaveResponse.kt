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
    val id: Int?=null,
    val type: String?=null,
    val startDate: String?=null,
    val endDate: String?=null,
    val approvedById: Int?=null,
    val approvedByName: String?=null,
    val status: String?=null,
    val reason: String?=null,
    val leaveBalance: Int?=null,
    val applyDays: Int?=null

)

