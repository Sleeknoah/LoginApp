package com.example.loginapp.core.cache

import android.content.SharedPreferences

internal interface EncryptedKeyValueManager {

    val sharedPreferences: SharedPreferences

    fun getValue(key: String): String?

    fun putValue(key: String, value: String): Boolean

    fun clearValueByKey(key: String)
}