package com.example.loginapp.modules.login.data.cache.maper

import com.example.loginapp.modules.login.data.cache.dto.CredDto
import com.example.loginapp.modules.login.data.cache.model.CredResponse
import com.example.loginapp.modules.login.data.datasource.api.model.LoginResponse
import com.example.loginapp.modules.login.data.datasource.dto.LoginDto
import javax.inject.Inject

internal interface CredDtoMapper {
    fun map (data: CredResponse): CredDto
}


internal class CredDtoMapperImpl @Inject constructor(
): CredDtoMapper {

    override fun map(data: CredResponse): CredDto {
        return  CredDto(
           username = data.username,
           password = data.password,
        )
    }

}