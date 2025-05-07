package com.example.figma_replicate.domain.model

data class Attendance(
    val id: Int? = null, // Nullable, auto-incremented by server
    val userId: Int, // Foreign key to User
    val date: String,
    val checkIn: String? = null, // Nullable, set when user checks in
    val checkOut: String? = null, // Nullable, set when user checks out
    val attendance: Attendances? = null // Nullable enum from schema
)