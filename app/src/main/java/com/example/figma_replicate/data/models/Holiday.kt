package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Holiday(
    val id: Int? = null,
    val title: String? = null,
    val startDate: String? = null,  // ISO 8601 format (e.g., "2025-12-25T00:00:00Z")
    val endDate: String? = null,
    val createdById: Int? = null
)
@Serializable
data class AddHoliday(
    val title: String,
    val startDate: String,  // ISO 8601 format (e.g., "2025-12-25T00:00:00Z")
    val endDate: String,
    val createdById: Int? = null
)