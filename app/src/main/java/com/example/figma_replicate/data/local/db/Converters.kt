package com.example.figma_replicate.data.local.db


import androidx.room.TypeConverter
import com.example.figma_replicate.domain.model.Attendances
import com.example.figma_replicate.domain.model.LeaveStatus
import com.example.figma_replicate.domain.model.LeaveType
import com.example.figma_replicate.domain.model.Role

class Converters {
    @TypeConverter
    fun fromRole(value: Role): String = value.name

    @TypeConverter
    fun toRole(value: String): Role = Role.valueOf(value)

    @TypeConverter
    fun fromLeaveType(value: LeaveType): String = value.name

    @TypeConverter
    fun toLeaveType(value: String): LeaveType = LeaveType.valueOf(value)

    @TypeConverter
    fun fromLeaveStatus(value: LeaveStatus): String = value.name

    @TypeConverter
    fun toLeaveStatus(value: String): LeaveStatus = LeaveStatus.valueOf(value)

    @TypeConverter
    fun fromAttendances(value: Attendances?): String? = value?.name

    @TypeConverter
    fun toAttendances(value: String?): Attendances? = value?.let { Attendances.valueOf(it) }
}