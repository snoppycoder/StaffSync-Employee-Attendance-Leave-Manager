package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LeaveRequest(
    val type: String,
    val startDate: String, // Format: "yyyy-MM-dd"
    val endDate: String,
    val reason: String
)
