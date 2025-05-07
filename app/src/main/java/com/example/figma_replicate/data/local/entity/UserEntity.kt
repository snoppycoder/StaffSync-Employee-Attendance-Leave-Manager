package com.example.figma_replicate.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.figma_replicate.domain.model.Role

@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey val id: Int? = null,
    val username: String,
    val password: String,
    val email: String,
    val role: Role = Role.EMPLOYEE,
    val points: Int = 100,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val deletedAt: String? = null,
    val token: String? = null
)