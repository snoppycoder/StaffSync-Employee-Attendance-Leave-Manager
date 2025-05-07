package com.example.figma_replicate.data.local.entity


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.figma_replicate.domain.model.Attendances

@Entity(tableName = "Attendance", indices = [Index(value = ["userId", "date"], unique = true)])
data class AttendanceEntity(
    @PrimaryKey val id: Int? = null,
    val userId: Int,
    val date: String,
    val checkIn: String? = null,
    val checkOut: String? = null,
    val attendance: Attendances? = null
)