package com.example.figma_replicate.data.models

import java.util.Date

data class Notification(
    val message: String,
    val id: Int,
    val userId: Int,
    val user: User,
    val createdAt: Date,

//    val leaveRequest: LeaveRequest,
//    val leaveRequestId: Int


)
