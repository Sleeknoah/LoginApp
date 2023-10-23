package com.example.loginapp.modules.login.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
internal interface LoginUIModule {


    companion object{
        @Provides
        fun provideDispatcherIO(): CoroutineDispatcher {
            return Dispatchers.IO
        }
    }
}