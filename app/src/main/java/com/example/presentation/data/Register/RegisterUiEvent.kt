package com.example.presentation.data.Register

sealed class RegisterUiEvent {

    data class nameChanged(val name: String) : RegisterUiEvent()
    data class emailChanged(val email: String) : RegisterUiEvent()
    data class passwordChanged(val password: String) : RegisterUiEvent()
    data class confirmPasswordChanged(val confirmPassword: String) : RegisterUiEvent()
    object registerButtonClicked : RegisterUiEvent()

}
