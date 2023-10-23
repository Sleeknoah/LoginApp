package com.example.loginapp.modules.login.domain.usecase.dto

sealed class LoginResult {
    data class LoginSuccess(
        val firstName: String,
        val lastName: String,
        val image: String,
        val email: String,
        val username: String,
        val token: String,
        val gender: String,
        val id: Int,
    ): LoginResult()

    data class LoginFailure(
        val message: String
    ): LoginResult()
}