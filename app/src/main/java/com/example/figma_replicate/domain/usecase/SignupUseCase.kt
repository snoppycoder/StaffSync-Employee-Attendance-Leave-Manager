package com.example.figma_replicate.domain.usecase

import com.example.figma_replicate.data.repository.AuthRepository
import com.example.figma_replicate.domain.model.User

class SignupUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String, email: String): Result<String> {
        // Business logic: Validate input
        if (username.isBlank() || password.isBlank() || email.isBlank()) {
            return Result.failure(Exception("Username, password, and email cannot be empty"))
        }
        if (password.length < 6) {
            return Result.failure(Exception("Password must be at least 6 characters"))
        }
        if (!email.contains("@")) {
            return Result.failure(Exception("Invalid email format"))
        }
        // Create User object and delegate to Repository
        val user = User(
            username = username,
            password = password,
            email = email
        )
        return authRepository.signup(user)
    }
}