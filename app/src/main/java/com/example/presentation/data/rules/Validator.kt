package com.example.presentation.data.rules

object Validator {

    fun validateName(name: String) : validationResult {
        return validationResult(
            (!name.isNullOrEmpty() && name.length >= 5)
        )
    }

    fun validateEmail(email: String) : validationResult {
        return validationResult(
            (!email.isNullOrEmpty())
        )
    }

    fun validatePassword(password: String) : validationResult {
        return validationResult(
            (!password.isNullOrEmpty() && password.length >= 8)
        )
    }

    fun validateConfirmPassword(confirmPassword: String) : validationResult {
        /* Not Implemented Yet */
        return validationResult(
            (true)
        )
    }

    fun validateHeight(height: Int) : validationResult {
        return validationResult(
            height > 60 && height < 220
        )
    }

    fun validateWeight(weight: Int) : validationResult {
        return validationResult(
            weight > 40 && weight < 120
        )
    }
}

data class validationResult (
    val status: Boolean = false
)