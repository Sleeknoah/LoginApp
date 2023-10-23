package com.example.loginapp.modules.login.data.mapper


import com.example.loginapp.modules.login.domain.entities.LoginEntityResult
import com.example.loginapp.modules.login.domain.entities.LoginUserResult
import com.example.loginapp.modules.login.data.datasource.dto.LoginDto
import javax.inject.Inject

internal interface LoginDtoToEntityMapper{
    fun map(dto: LoginDto): LoginEntityResult

    fun map(exception: Exception): LoginEntityResult
}

internal class LoginDtoToEntityMapperImpl @Inject constructor(): LoginDtoToEntityMapper {
    override fun map(dto: LoginDto): LoginEntityResult {
        return LoginEntityResult.LoginSuccess(
            user = LoginUserResult(
                firstName = dto.firstName,
                lastName = dto.lastName,
                image = dto.image,
                email = dto.email,
                username = dto.username,
                token = dto.token,
                gender = dto.gender,
                id = dto.id,
            )
        )
    }

    override fun map(exception: Exception): LoginEntityResult {
        return  LoginEntityResult.LoginFailure(
            message = exception.message ?: "Oops an error just occurred. Please try again"
        )
    }

}