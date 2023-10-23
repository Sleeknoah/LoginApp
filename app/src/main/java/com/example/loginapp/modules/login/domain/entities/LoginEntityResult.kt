package com.example.loginapp.modules.login.domain.entities

sealed class LoginEntityResult{
    data class LoginSuccess(val user: LoginUserResult): LoginEntityResult()
    data class LoginFailure(val message: String): LoginEntityResult()
}

data class LoginUserResult(
    val firstName: String?,
    val lastName: String?,
    val image: String?,
    val email: String?,
    val username: String?,
    val token: String?,
    val gender: String?,
    val id: Int?
)