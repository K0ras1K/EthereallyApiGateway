package ru.k0ras1k.ethereally.utils

object Validator {
    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^([a-zA-Z0-9_.+-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+\$")
        return email.matches(emailRegex)
    }
}