//package com.example.figma_replicate.data.repository
//
//import android.net.http.HttpException
//import com.example.figma_replicate.data.models.User
//import java.io.IOException
//
//
//// Update user data by ID
//suspend fun updateUser(id: Int, user: User): Result<User> {
//    return try {
//        val response = apiServiceInterface.updateUser(id, user)
//        Result.success(response)
//    } catch (e: HttpException) {
//        Result.failure(e)
//    } catch (e: IOException) {
//        Result.failure(e)
//    }
//}