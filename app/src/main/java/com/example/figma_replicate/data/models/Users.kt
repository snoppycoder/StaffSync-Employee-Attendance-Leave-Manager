package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String? = null,
    val fullName: String? = null,
    val password: String? = null,
    val email: String? = null,
    val gender: String? = null,
    val employmentType: String? = null,
    val designation: String? = null,
    val role: UserRole? = null ,
    val dateOfBirth: String? = null

)
