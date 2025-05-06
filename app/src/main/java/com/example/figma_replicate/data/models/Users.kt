package com.example.figma_replicate.data.models
import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val role: String,
    val points: Int,
    val createdAt: String,      // ISO 8601 timestamp
    val updatedAt: String,      // ISO 8601 timestamp
    val deletedAt: String? = null
)
@Serializable
data class Profile(
    val id: Int,
    val fullName: String,
    val gender: String,
    val employmentType: String,
    val designation: String,
    val userId: Int,
    val dateOfBirth: String,    // ISO 8601 timestamp
    val createdAt: String,      // ISO 8601 timestamp
    val updatedAt: String,      // ISO 8601 timestamp
    val deletedAt: String? = null
)