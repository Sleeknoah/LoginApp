package com.example.loginapp.modules.login.data.di

import com.example.loginapp.core.cache.CredentialManagerImpl
import com.example.loginapp.modules.login.data.cache.CredManager
import com.example.loginapp.modules.login.data.cache.maper.CredDtoMapper
import com.example.loginapp.modules.login.data.cache.maper.CredDtoMapperImpl
import com.example.loginapp.modules.login.data.datasource.CacheDataSource
import com.example.loginapp.modules.login.data.datasource.CacheLoginDataImpl
import com.example.loginapp.modules.login.data.mapper.LoginDtoToEntityMapper
import com.example.loginapp.modules.login.data.mapper.LoginDtoToEntityMapperImpl
import com.example.loginapp.modules.login.data.mapper.LoginRepositoryImpl
import com.example.loginapp.modules.login.data.datasource.api.LoginService
import com.example.loginapp.modules.login.data.datasource.remote.LoginDataSource
import com.example.loginapp.modules.login.data.datasource.remote.LoginDataSourceImpl
import com.example.loginapp.modules.login.data.datasource.remote.mapper.LoginDtoMapper
import com.example.loginapp.modules.login.data.datasource.remote.mapper.LoginDtoMapperImpl
import com.example.loginapp.modules.login.data.mapper.CredDtoToEntityMapper
import com.example.loginapp.modules.login.data.mapper.CredDtoToEntityMapperImpl
import com.example.loginapp.modules.login.domain.entities.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal  interface LoginModule {

    @Binds
    fun bindDtoMapper(
        impl: LoginDtoMapperImpl
    ): LoginDtoMapper

    @Binds
    fun bindCredDtoMapper(
        impl: CredDtoMapperImpl
    ): CredDtoMapper

    @Binds
    fun bindDataSource(
        impl: LoginDataSourceImpl
    ): LoginDataSource

    @Binds
    fun bindCacheDataSource(
        impl: CacheLoginDataImpl
    ): CacheDataSource

    @Binds
    fun bindDtoEntityMapper(
        impl: LoginDtoToEntityMapperImpl
    ): LoginDtoToEntityMapper

    @Binds
    fun bindCredDtoEntityMapper(
        impl: CredDtoToEntityMapperImpl
    ): CredDtoToEntityMapper

    @Binds
    fun bindLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository

    companion object{
        @Provides
        fun provideService(retrofit: Retrofit): LoginService {
            return retrofit.create(LoginService::class.java)
        }
    }

}