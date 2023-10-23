package com.example.loginapp.core.di

import android.content.SharedPreferences
import com.example.loginapp.core.cache.CredentialManagerImpl
import com.example.loginapp.core.cache.EncryptedKeyValueManager
import com.example.loginapp.core.cache.EncryptedKeyValueManagerImpl
import com.example.loginapp.modules.login.data.cache.CredManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface BaseModule {

    @Binds
    fun bindTokenManager(tokenManager: CredentialManagerImpl): CredManager

    @Binds
    fun bindEncryptedStorage(manager: EncryptedKeyValueManagerImpl): EncryptedKeyValueManager

    companion object{
        @Singleton
        @Provides
        fun provideSharedPreferences(keyValueManager: EncryptedKeyValueManager): SharedPreferences {
            return keyValueManager.sharedPreferences
        }
    }

}