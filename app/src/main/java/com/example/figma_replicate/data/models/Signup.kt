package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SignupRequest(
    val username: String? = null,
    val fullName: String? = null,
    val email: String? = null,
    val designation: String? = null,
    val employmentType: String? = null,
    val gender: String? = null,
    val dob: String? = null,
    val password: String? = null,
    val role: UserRole? = UserRole.EMPLOYEE,
    val dateOfBirth: String? = null
)


@Serializable
data class SignupResponse(
    val id: String,
    val username: String,
    val role: UserRole,
    val token: String? = null
)