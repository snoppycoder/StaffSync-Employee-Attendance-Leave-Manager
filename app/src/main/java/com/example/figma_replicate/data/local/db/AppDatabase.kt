package com.example.figma_replicate.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.figma_replicate.data.local.DAO.AttendanceDao
import com.example.figma_replicate.data.local.DAO.LeaveRequestDao
import com.example.figma_replicate.data.local.DAO.UserDao
import com.example.figma_replicate.data.local.entity.AttendanceEntity
import com.example.figma_replicate.data.local.entity.LeaveRequestEntity
import com.example.figma_replicate.data.local.entity.UserEntity

@Database(entities = [UserEntity::class, LeaveRequestEntity::class, AttendanceEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun leaveRequestDao(): LeaveRequestDao
    abstract fun attendanceDao(): AttendanceDao
}