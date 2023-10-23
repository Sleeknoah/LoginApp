package com.example.loginapp.modules.login.domain.usecase

import com.example.loginapp.modules.login.domain.entities.CredEntityResult
import com.example.loginapp.modules.login.domain.usecase.dto.LoginResult
import com.example.loginapp.modules.login.domain.entities.LoginEntityResult
import com.example.loginapp.modules.login.domain.entities.LoginRepository
import com.example.loginapp.modules.login.domain.usecase.dto.CredResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
 interface GetSavedCredentialsUsecase{
    suspend fun execute(): Flow<CredResult>
}

internal class GetSavedCredentialsUsecaseImpl @Inject constructor(
    private val repository: LoginRepository
): GetSavedCredentialsUsecase {
    override suspend fun execute(): Flow<CredResult> {
       return when(val result = repository.getSavedCred()){

           is CredEntityResult.Cred ->
               flow<CredResult> {
                    emit(
                        CredResult.Results(
                        username = result.cred.username ?: "",
                        password = result.cred.password ?: "",
                        )
                    )
               }
       }
    }

}