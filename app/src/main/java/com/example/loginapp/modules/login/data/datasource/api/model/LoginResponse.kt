package com.example.loginapp.modules.login.data.datasource.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

internal data class LoginResponse(
    @Json(name = "firstName") val firstName: String,
    @Json(name = "lastName")val lastName: String,
    @Json(name = "username")val username: String,
    @Json(name = "email")val email: String,
    @Json(name = "id")val id: Int,
    @Json(name = "token")val token: String,
    @Json(name = "gender")val gender: String,
    @Json(name = "image")val image: String
)