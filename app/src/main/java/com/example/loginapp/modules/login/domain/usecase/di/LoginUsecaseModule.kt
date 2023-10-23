package com.example.loginapp.modules.login.domain.usecase.di

import com.example.loginapp.modules.login.domain.usecase.DeleteCredentialsUsecase
import com.example.loginapp.modules.login.domain.usecase.DeleteCredentialsUsecaseImpl
import com.example.loginapp.modules.login.domain.usecase.EmailValidationUseCaseImpl
import com.example.loginapp.modules.login.domain.usecase.LoginUseCaseImpl
import com.example.loginapp.modules.login.domain.usecase.EmailValidationUseCase
import com.example.loginapp.modules.login.domain.usecase.GetSavedCredentialsUsecase
import com.example.loginapp.modules.login.domain.usecase.GetSavedCredentialsUsecaseImpl
import com.example.loginapp.modules.login.domain.usecase.LoginUseCase
import com.example.loginapp.modules.login.domain.usecase.SaveCredentialsUsecase
import com.example.loginapp.modules.login.domain.usecase.SaveCredentialsUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

internal interface LoginUsecaseModule {
    @Binds
    fun bindLoginUsecase(impl: LoginUseCaseImpl): LoginUseCase

    @Binds
    fun bindEmailValidationUsecase(impl: EmailValidationUseCaseImpl): EmailValidationUseCase
   @Binds
    fun bindSaveCredentialsUsecase(impl: SaveCredentialsUsecaseImpl): SaveCredentialsUsecase

    @Binds
    fun bindDeleteCredentialsUsecase(impl: DeleteCredentialsUsecaseImpl): DeleteCredentialsUsecase

    @Binds
    fun bindGetSavedCredentialsUsecase(impl: GetSavedCredentialsUsecaseImpl): GetSavedCredentialsUsecase

}