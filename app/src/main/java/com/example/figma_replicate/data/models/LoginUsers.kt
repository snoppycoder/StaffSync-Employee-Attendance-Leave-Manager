package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String? = null,
    val password: String? = null
)
@Serializable
data class LoginResponse(
    val id: String,
    val username: String,
    val role: UserRole,
    val token: String? = null
)