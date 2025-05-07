package com.example.figma_replicate.data.repository


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.figma_replicate.data.local.db.AppDatabase
import com.example.figma_replicate.data.local.entity.UserEntity
import com.example.figma_replicate.data.network.ApiService
import com.example.figma_replicate.domain.model.User
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore("auth_prefs")
val TOKEN_KEY = stringPreferencesKey("auth_token")

class AuthRepository(
    private val apiService: ApiService,
    private val context: Context,
    private val db: AppDatabase
) {
    suspend fun signup(user: User): Result<String> {
        return try {
            val response = apiService.signup(user)
            if (response.isSuccessful) {
                response.body()?.token?.let { token ->
                    context.dataStore.edit { preferences ->
                        preferences[TOKEN_KEY] = token
                    }
                    val userEntity = UserEntity(
                        id = user.id,
                        username = user.username,
                        password = user.password,
                        email = user.email,
                        role = user.role,
                        points = user.points,
                        createdAt = user.createdAt,
                        updatedAt = user.updatedAt,
                        deletedAt = user.deletedAt,
                        token = token
                    )
                    db.userDao().insertUser(userEntity)
                    Result.success("Signup successful")
                } ?: Result.failure(Exception("No token received"))
            } else {
                Result.failure(Exception("Signup failed: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(username: String, password: String): Result<String> {
        return try {
            val credentials = mapOf("username" to username, "password" to password)
            val response = apiService.login(credentials)
            if (response.isSuccessful) {
                response.body()?.token?.let { token ->
                    context.dataStore.edit { preferences ->
                        preferences[TOKEN_KEY] = token
                    }
                    val userEntity = UserEntity(
                        id = response.body()?.id,
                        username = username,
                        password = password,
                        email = response.body()?.email ?: "",
                        role = response.body()?.role ?: Role.EMPLOYEE,
                        points = response.body()?.points ?: 100,
                        createdAt = response.body()?.createdAt,
                        updatedAt = response.body()?.updatedAt,
                        deletedAt = response.body()?.deletedAt,
                        token = token
                    )
                    db.userDao().insertUser(userEntity)
                    Result.success("Login successful")
                } ?: Result.failure(Exception("No token received"))
            } else {
                Result.failure(Exception("Login failed: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getToken(): String? {
        return context.dataStore.data.first()[TOKEN_KEY]
    }
}