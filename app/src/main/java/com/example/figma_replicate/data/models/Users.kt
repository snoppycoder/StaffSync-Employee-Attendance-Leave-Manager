package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val fullName: String,
    val password: String,
    val email: String,
    val gender: String,
    val employmentType: String,
    val designation: String,
    val role: String,
    val dateOfBirth:String
)
