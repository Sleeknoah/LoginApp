package com.example.loginapp.modules.login.domain.usecase

import java.util.regex.Pattern
import javax.inject.Inject

interface EmailValidationUseCase {

    fun execute(email: String): Boolean
}

internal class EmailValidationUseCaseImpl @Inject constructor() : EmailValidationUseCase {
    override fun execute(email: String): Boolean {
        val emailPattern =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
        return emailPattern.matcher(email).matches()
    }

}