package com.example.figma_replicate.data.network
import com.example.figma_replicate.data.models.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServiceInterface {

//    @GET("api/users")
//    suspend fun getUsers(): List<User>

    @POST("api/signup")
    suspend fun signup(@Body user: User): User
}
