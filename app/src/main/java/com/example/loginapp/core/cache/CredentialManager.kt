package com.example.loginapp.core.cache

import android.content.SharedPreferences
import com.example.loginapp.modules.login.data.cache.CredManager
import javax.inject.Inject

internal class CredentialManagerImpl @Inject constructor(private  val sharedPreferences: SharedPreferences)
    : CredManager{
    override var email: String?
        get() = sharedPreferences.getString(EMAIl, null)
        set(value) {
            sharedPreferences.edit().putString(EMAIl, value).apply()
        }
    override var password: String?
        get() = sharedPreferences.getString(PASSWORD, null)
        set(value) {
            sharedPreferences.edit().putString(PASSWORD, value).apply()
        }

    override var delete: () -> Unit
        get() = { }
        set(value) {
            sharedPreferences.edit().clear().apply()
        }



    companion object{
        const val EMAIl = "email"
        const val PASSWORD = "password"
    }
}