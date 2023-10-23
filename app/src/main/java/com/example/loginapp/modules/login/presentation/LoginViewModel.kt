package com.example.loginapp.modules.login.presentation

import androidx.lifecycle.ServiceLifecycleDispatcher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginapp.modules.login.domain.usecase.DeleteCredentialsUsecase
import com.example.loginapp.modules.login.domain.usecase.EmailValidationUseCase
import com.example.loginapp.modules.login.domain.usecase.GetSavedCredentialsUsecase
import com.example.loginapp.modules.login.domain.usecase.LoginUseCase
import com.example.loginapp.modules.login.domain.usecase.SaveCredentialsUsecase
import com.example.loginapp.modules.login.domain.usecase.dto.CredResult
import com.example.loginapp.modules.login.domain.usecase.dto.LoginResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
 private val loginUsecase: LoginUseCase,
 private val saveCredentialsUsecase: SaveCredentialsUsecase,
 private val deleteCredentialsUsecase: DeleteCredentialsUsecase,
 private val getSavedCredentialsUsecase: GetSavedCredentialsUsecase,
 private val dispatcherIO: CoroutineDispatcher
): ViewModel()
{

    private val _uiState = MutableStateFlow<ViewState>(ViewState.Empty)
    val uiState: StateFlow<ViewState> = _uiState

    fun login(username: String, password: String){
            viewModelScope.launch{
                _uiState.value = ViewState.LoadingState
                if(username.isNotEmpty() && password.isNotEmpty()) {
                    loginUsecase.execute(username, password)
                        .flowOn(dispatcherIO).collect {result ->
                            when(result){
                                is LoginResult.LoginSuccess ->{
                                    _uiState.value = ViewState.Success(
                                        firstName = result.firstName,
                                        lastName = result.lastName,
                                        image = result.image,
                                        email = result.email,
                                        username = result.username,
                                        token = result.token,
                                        gender = result.gender,
                                        id = result.id
                                    )
                                    _uiState.value = ViewState.Empty
                                }

                                is LoginResult.LoginFailure -> _uiState.value = ViewState.Error(
                                    errorMessage = result.message
                                )
                            }

                        }
                }
            }

    }

    fun saveCred(username: String, password: String){
        viewModelScope.launch {
            if(username.isNotEmpty() && password.isNotEmpty()){
                saveCredentialsUsecase.execute(username, password)
            }
        }
    }

    fun deleteCred(){
        viewModelScope.launch {
                deleteCredentialsUsecase.execute()
        }
    }

    fun getSavedCred(){
        viewModelScope.launch {
            getSavedCredentialsUsecase.execute()
                .flowOn(dispatcherIO).collect{
                    when(it){
                        is CredResult.Results -> _uiState.value = ViewState.LoginCred(
                            username = it.username,
                            password = it.password,
                        )
                    }
                }
        }
    }

    sealed class ViewState{
        data object LoadingState : ViewState()
        data object Empty : ViewState()
        data class LoginCred(
            val username: String,
            val password: String,
        ): ViewState()
        data class Success(
            val firstName: String,
            val lastName: String,
            val image: String,
            val email: String,
            val username: String,
            val token: String,
            val gender: String,
            val id: Int,
        ): ViewState()
        data class Error(val errorMessage: String): ViewState()
    }
}