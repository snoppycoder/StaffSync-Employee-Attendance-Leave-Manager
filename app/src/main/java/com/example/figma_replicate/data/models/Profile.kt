package com.example.figma_replicate.data.models

import java.time.LocalDateTime

data class Profile(
    val id: Int,
    val fullName: String,
    val gender: String,
    val employmentType: String,
    val designation: String,
    val userId: Int,
    val dateOfBirth: LocalDateTime, // You can use a library like kotlinx.serialization or Moshi to convert this from string to LocalDateTime
    val createdAt: LocalDateTime, // Same as above for the date-time fields
    val updatedAt: LocalDateTime, // Same as above
    val deletedAt: LocalDateTime? // Nullable field in case the value is null
)
