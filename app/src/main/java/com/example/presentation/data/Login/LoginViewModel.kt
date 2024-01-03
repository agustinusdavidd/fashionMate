package com.example.presentation.data.Login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.presentation.data.rules.Validator
import com.example.presentation.navigation.Graph
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(private val navHostController: NavHostController) : ViewModel(){

    private val TAG = "LoginViewModel"

    var loginUiState = mutableStateOf(LoginUiState())
    var validationStatus = mutableStateOf(false)
    var progress = mutableStateOf(false)

    fun  onEvent(event: LoginUiEvent){

        when(event){
            is LoginUiEvent.emailChanged -> {
                loginUiState.value = loginUiState.value.copy(
                    email = event.email
                )
            }
            is LoginUiEvent.passwordChanged -> {
                loginUiState.value = loginUiState.value.copy(
                    password = event.password
                )
            }
            is LoginUiEvent.loginButtonClicked -> {
                login()
            }
        }
        validateData()
    }

    private fun validateData() {

        val isEmailValid = Validator.validateEmail(
            email = loginUiState.value.email
        )

        val isPasswordValid = Validator.validatePassword(
            password = loginUiState.value.password
        )

        Log.d(TAG, "Validation Data Result")
        Log.d(TAG, "Name : $isEmailValid")
        Log.d(TAG, "Name : $isPasswordValid")

        loginUiState.value = loginUiState.value.copy(
            emailError = isEmailValid.status,
            passwordError = isPasswordValid.status
        )

        validationStatus.value = isEmailValid.status && isPasswordValid.status

    }

    private fun login() {

        progress.value = true

        val email = loginUiState.value.email
        val password = loginUiState.value.password

        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Complete")
                Log.d(TAG, "${it.isSuccessful}")

                progress.value = false

                if(it.isSuccessful){
                    navHostController.popBackStack()
                    navHostController.navigate(Graph.HOME)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Failure")
                Log.d(TAG, "${it.message}")
            }
    }
}