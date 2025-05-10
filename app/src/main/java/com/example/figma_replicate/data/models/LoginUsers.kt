package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind.INT

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)
@Serializable
data class LoginResponse(
    val id: Int,
    val username: String,
    val role: UserRole,
    val token: String? = null
)