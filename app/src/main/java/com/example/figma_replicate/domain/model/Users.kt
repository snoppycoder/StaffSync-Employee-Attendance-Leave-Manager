package com.example.figma_replicate.domain.model
import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: Int? = null, // Nullable since it's auto-incremented on the server
    val username: String,
    val password: String,
    val email: String,
    val role: Role = Role.EMPLOYEE, // Enum from schema
    val points: Int = 100, // Default value from schema
    val createdAt: String? = null, // Nullable, defaulted by server
    val updatedAt: String? = null, // Nullable, updated by server
    val deletedAt: String? = null, // Nullable for soft delete
    val token: String? = null // Added for authentication, not in schema but implied
)

//data class User(
//    val id: Int,
//    val username: String,
//    val password: String,
//    val email: String,
//    val role: String,
//    val points: Int,
//    val createdAt: String,      // ISO 8601 timestamp
//    val updatedAt: String,      // ISO 8601 timestamp
//    val deletedAt: String? = null
//)