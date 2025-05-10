package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginUsers(
    val username: String? = null,
    val password: String? = null
)
