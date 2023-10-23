package com.example.loginapp.modules.login.data.datasource.remote.mapper

import com.example.loginapp.modules.login.data.datasource.api.model.LoginResponse
import com.example.loginapp.modules.login.data.datasource.dto.LoginDto
import javax.inject.Inject

internal interface LoginDtoMapper {
    fun map (data: LoginResponse): LoginDto
}


internal class LoginDtoMapperImpl @Inject constructor(
): LoginDtoMapper {

    override fun map(data: LoginResponse): LoginDto {
        return  LoginDto(
           firstName = data.firstName,
           lastName = data.lastName,
           image = data.image,
           email = data.email,
           username = data.username,
           token = data.token,
           gender = data.gender,
           id = data.id,
        )
    }

//    private fun mapToken(data: LoginResponse): TokenDto {
//        return TokenDto(
//            authToken = data.data.token,
//            refreshToken = ""
//        )
//    }

}