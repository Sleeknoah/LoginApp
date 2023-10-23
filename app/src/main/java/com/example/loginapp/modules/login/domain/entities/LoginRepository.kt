package com.example.loginapp.modules.login.domain.entities


interface LoginRepository {
    suspend fun login(
        email: String,
        password: String,
    ): LoginEntityResult

    fun saveCred(
        email: String,
        password: String,
    )

    fun deleteCred()
    fun getSavedCred(): CredEntityResult


}