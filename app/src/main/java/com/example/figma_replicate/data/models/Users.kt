package com.example.figma_replicate.data.models
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