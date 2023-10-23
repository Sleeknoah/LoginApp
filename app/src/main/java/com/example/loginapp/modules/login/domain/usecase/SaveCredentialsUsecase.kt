package com.example.loginapp.modules.login.domain.usecase

import com.example.loginapp.modules.login.domain.usecase.dto.LoginResult
import com.example.loginapp.modules.login.domain.entities.LoginEntityResult
import com.example.loginapp.modules.login.domain.entities.LoginRepository
import javax.inject.Inject
 interface SaveCredentialsUsecase{
    suspend fun execute (
        email: String,
        password: String,
    )
}

internal class SaveCredentialsUsecaseImpl @Inject constructor(
    private val repository: LoginRepository
): SaveCredentialsUsecase {
    override suspend fun execute(
        email: String,
        password: String,
    ) {
        repository.saveCred(email = email, password = password)
    }

}