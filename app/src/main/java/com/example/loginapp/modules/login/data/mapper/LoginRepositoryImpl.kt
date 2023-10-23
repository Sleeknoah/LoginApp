package com.example.loginapp.modules.login.data.mapper

import android.util.Log
import com.example.loginapp.modules.login.data.datasource.CacheDataSource
import com.example.loginapp.modules.login.data.datasource.api.request.LoginRequest
import com.example.loginapp.modules.login.domain.entities.LoginEntityResult
import com.example.loginapp.modules.login.domain.entities.LoginRepository
import com.example.loginapp.modules.login.data.datasource.remote.LoginDataSource
import com.example.loginapp.modules.login.domain.entities.CredEntityResult
import com.example.loginapp.modules.login.domain.entities.CredResults
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource,
    private val loginCacheDataSource: CacheDataSource,
    private val mapper: LoginDtoToEntityMapper,
    private val credMapper: CredDtoToEntityMapper,
): LoginRepository {
    override suspend fun login(
        email: String,
        password: String,
    ): LoginEntityResult {
        return try {
            dataSource.login(
                email, password
            ).let {
                mapper.map(it)
            }
        }catch (e: Exception){
            e.localizedMessage?.let { Log.e("Error", it) }
            mapper.map(e)
        }
    }

    override fun saveCred(email: String, password: String) {
        loginCacheDataSource.saveCredentials(
            body = LoginRequest(
                email = email,
                password = password,
            )
        )
    }

    override fun deleteCred() {
        loginCacheDataSource.deleteCredentials()
    }

    override fun getSavedCred(): CredEntityResult {
        val credDto = loginCacheDataSource.getSavedCred()
        return credMapper.map(credDto)
    }


}