package com.example.loginapp.modules.login.data.cache

interface CredManager {
    var email: String?
    var password: String?
    var delete: () -> Unit
}