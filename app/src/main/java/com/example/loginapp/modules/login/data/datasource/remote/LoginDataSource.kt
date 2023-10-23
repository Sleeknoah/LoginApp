package  com.example.loginapp.modules.login.data.datasource.remote

import com.example.loginapp.core.network.eitherResponseOrThrow
import com.example.loginapp.modules.login.data.datasource.api.LoginService
import com.example.loginapp.modules.login.data.datasource.api.request.LoginRequest
import com.example.loginapp.modules.login.data.datasource.dto.LoginDto
import com.example.loginapp.modules.login.data.datasource.remote.mapper.LoginDtoMapper
import javax.inject.Inject

internal interface LoginDataSource {
    suspend fun login(
        email: String,
        password: String,
    ): LoginDto
}

internal class LoginDataSourceImpl @Inject constructor(
    private val service: LoginService,
    private val dtoMapper: LoginDtoMapper
): LoginDataSource{
    override suspend fun login(
        email: String,
        password: String,
    ): LoginDto {
        val input  = LoginRequest(
            email = email,
            password = password,
        )
        return eitherResponseOrThrow { service.login(input) }.let {
                dtoMapper.map(it)
            }
        }
}