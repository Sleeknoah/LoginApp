package com.example.loginapp.core.network

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Response

suspend fun<T> eitherResponseOrThrow(networkCall: suspend () -> Response<T>): T{
    try {
        val response  = networkCall.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            val message = response.errorBody()?.extractErrorMessage()
                ?: "Request failed. Please try again later"
            throw CustomException(message)
        }
    }catch (e: Exception){
        throw e
    }
}


@Throws
fun ResponseBody.extractErrorMessage(): String? {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val jsonAdapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)
    val errorResponse = jsonAdapter.fromJson(this.string())
    return errorResponse?.message
}