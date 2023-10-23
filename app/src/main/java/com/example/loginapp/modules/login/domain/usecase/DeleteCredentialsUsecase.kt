package com.example.loginapp.modules.login.domain.usecase

import com.example.loginapp.modules.login.domain.usecase.dto.LoginResult
import com.example.loginapp.modules.login.domain.entities.LoginEntityResult
import com.example.loginapp.modules.login.domain.entities.LoginRepository
import javax.inject.Inject
 interface DeleteCredentialsUsecase{
    suspend fun execute ()
}

internal class DeleteCredentialsUsecaseImpl @Inject constructor(
    private val repository: LoginRepository
): DeleteCredentialsUsecase {
    override suspend fun execute() {
        repository.deleteCred()
    }

}