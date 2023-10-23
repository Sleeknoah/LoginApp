package com.example.loginapp.core.di

import com.example.loginapp.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.readTimeout(60, TimeUnit.SECONDS)
        client.connectTimeout(60, TimeUnit.SECONDS)
        client.addInterceptor {
            val original = it.request()
            val builder = original.newBuilder().method(original.method, original.body)

            val response = it.proceed(builder.build())
            response.headers[AUTHORIZATION_TOKEN]?.let { authToken ->
//                if (authToken.isNotBlank() && authToken != tokenManager.authToken) {
//                    tokenManager.authToken = authToken
//                }
            }
            response
        }
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(loggingInterceptor)
        return client.build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("${BuildConfig.BASE_URL}/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }
    companion object {
        const val AUTHORIZATION_TOKEN = "Authorization"
        const val REFRESH_TOKEN = "X-Refresh-Token"

        fun createHeader(token: String?): String = "Bearer $token"
    }
}