package com.example.figma_replicate.domain.usecase


import com.example.figma_replicate.data.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<String> {
        // Business logic: Validate input
        if (username.isBlank() || password.isBlank()) {
            return Result.failure(Exception("Username and password cannot be empty"))
        }
        // Delegate to Repository
        return authRepository.login(username, password)
    }
}