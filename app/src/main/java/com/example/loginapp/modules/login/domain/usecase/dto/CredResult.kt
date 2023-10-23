package com.example.loginapp.modules.login.domain.usecase.dto

sealed class CredResult {
    data class Results(
        val username: String,
        val password: String,
    ): CredResult()
}