package com.example.loginapp.modules.login.domain.usecase

import com.example.loginapp.modules.login.domain.usecase.dto.LoginResult
import com.example.loginapp.modules.login.domain.entities.LoginEntityResult
import com.example.loginapp.modules.login.domain.entities.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
 interface LoginUseCase{
    suspend fun execute (
        username: String,
        password: String,
    ): Flow<LoginResult>
}

internal class LoginUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
): LoginUseCase {
    override suspend fun execute(
        username: String,
        password: String,
    ): Flow<LoginResult> {
        return when(
            val result = repository.login(
                email = username,
                password = password,
            )
        ){
            is LoginEntityResult.LoginSuccess -> flow{
                emit(LoginResult.LoginSuccess(
                    firstName = result.user.firstName ?: "",
                    lastName = result.user.lastName ?: "",
                    image = result.user.image ?: "",
                    email = result.user.email ?: "",
                    username = result.user.username ?: "",
                    token = result.user.token ?: "",
                    gender = result.user.gender ?: "",
                    id = result.user.id ?: 0,
                ))
            }
            is LoginEntityResult.LoginFailure -> flow{
                emit(LoginResult.LoginFailure(result.message))
            }
        }
    }

}