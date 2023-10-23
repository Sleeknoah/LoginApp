package com.example.loginapp.core.cache

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.loginapp.core.cache.EncryptedKeyValueManager
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Inject

class EncryptedKeyValueManagerImpl @Inject constructor(@ApplicationContext private val applicationContext: Context):
    EncryptedKeyValueManager {

    private val sharedPrefsFile: String = "login"
    private val mainKey =
        MasterKey.Builder(applicationContext).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()


    override val sharedPreferences: SharedPreferences
        get() = EncryptedSharedPreferences.create(
            applicationContext,
            sharedPrefsFile,
            mainKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    override fun getValue(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun putValue(key: String, value: String): Boolean {
        return with(sharedPreferences.edit()){
            putString(key, value)
                .commit()
        }
    }

    override fun clearValueByKey(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}