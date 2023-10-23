package com.example.loginapp.modules.login.data.datasource

import com.example.loginapp.modules.login.data.cache.CredManager
import com.example.loginapp.modules.login.data.cache.dto.CredDto
import com.example.loginapp.modules.login.data.datasource.api.request.LoginRequest
import com.example.loginapp.modules.login.domain.entities.CredEntityResult
import com.example.loginapp.modules.login.domain.entities.CredResults
import javax.inject.Inject

internal interface CacheDataSource{
    fun saveCredentials(body: LoginRequest): Boolean
    fun deleteCredentials(): Boolean
    fun getSavedCred(): CredDto
}

internal class CacheLoginDataImpl @Inject constructor(
    private val credManager: CredManager
): CacheDataSource {

    override fun saveCredentials(body: LoginRequest): Boolean {
        credManager.email = body.email
        credManager.password = body.password
        return true
    }

    override fun deleteCredentials(): Boolean {
        val delete =  credManager.delete
        return true
    }

    override fun getSavedCred(): CredDto {
        return CredDto(
            username = credManager.email ?: "",
            password = credManager.password ?: "",
        )
    }

}