package com.example.figma_replicate.data.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
enum class AttendanceStatus {
    PRESENT,
    ABSENT
}

@Serializable
data class CheckInResponse(
    val message: String,
    val attendance: AttendanceRecord
)

@Serializable
data class CheckOutResponse(
    val message: String,
    val attendance: AttendanceRecord
)

@Serializable
data class AttendanceRecord(
    val id: Int,
    @SerialName("checkIn") val checkIn: String? = null,
    @SerialName("checkOut") val checkOut: String? = null,
    val attendance: AttendanceStatus? = null,
    val date: String? = null,
    @SerialName("createdAt") val createdAt: String? = null,
    val user: UserInfo? = null
)

@Serializable
data class UserInfo(
    val username: String,
    val profile: ProfileInfo
)

@Serializable
data class ProfileInfo(
    @SerialName("fullName") val fullName: String
)

@Serializable
data class AttendanceStats(
    @SerialName("totalPresent") val totalPresent: Int,
    @SerialName("totalAbsent") val totalAbsent: Int
)