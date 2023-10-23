package com.example.loginapp.modules.login.data.datasource.api

import com.example.loginapp.modules.login.data.datasource.api.model.LoginResponse
import com.example.loginapp.modules.login.data.datasource.api.request.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

internal interface LoginService {

    @POST("/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}