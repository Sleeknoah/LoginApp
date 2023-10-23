package  com.example.loginapp.modules.login.data.datasource.dto

internal data class LoginDto(
    val firstName: String,
    val lastName: String,
    val id: Int,
    val gender: String,
    val email: String,
    val image: String,
    val token: String,
    val username: String,
)
