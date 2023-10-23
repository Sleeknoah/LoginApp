package com.example.loginapp.modules.login.data.mapper

import com.example.loginapp.modules.login.data.cache.dto.CredDto
import com.example.loginapp.modules.login.domain.entities.CredEntityResult
import com.example.loginapp.modules.login.domain.entities.CredResults
import javax.inject.Inject

internal interface CredDtoToEntityMapper {
    fun map(dto: CredDto): CredEntityResult
}

internal class CredDtoToEntityMapperImpl @Inject constructor():
    CredDtoToEntityMapper {
    override fun map(dto: CredDto): CredEntityResult {
        return CredEntityResult.Cred(
             CredResults(
                 username = dto.username,
                 password = dto.password,
             )
        )
    }

}