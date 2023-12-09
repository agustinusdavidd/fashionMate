package com.example.presentation.data.Login

sealed class LoginUiEvent {
    data class emailChanged(val email: String) : LoginUiEvent()
    data class passwordChanged(val password: String) : LoginUiEvent()
    object loginButtonClicked : LoginUiEvent()

}
