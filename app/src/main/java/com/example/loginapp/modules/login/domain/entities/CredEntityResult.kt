package com.example.loginapp.modules.login.domain.entities

sealed class CredEntityResult{
    data class Cred(val cred: CredResults): CredEntityResult()
}

data class CredResults(
    val username: String?,
    val password: String?,
)