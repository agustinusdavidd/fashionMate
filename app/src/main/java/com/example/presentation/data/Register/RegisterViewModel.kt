package com.example.presentation.data.Register

import Route
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.presentation.data.rules.Validator
import com.example.presentation.navigation.Auth
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel(private val navHostController: NavHostController) : ViewModel() {

    private val TAG = "RegisterViewModel"

    var registerUiState = mutableStateOf(RegisterUiState())

    var validationStatus = mutableStateOf(false)

    var progress = mutableStateOf(false)

    fun onEvent(event: RegisterUiEvent){

        validateData()

        when(event){
            is RegisterUiEvent.nameChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    name = event.name
                )
                printState()
            }
            is RegisterUiEvent.emailChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    email = event.email
                )
                printState()
            }
            is RegisterUiEvent.passwordChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    password = event.password
                )
                printState()
            }
            is RegisterUiEvent.confirmPasswordChanged -> {
                registerUiState.value = registerUiState.value.copy(
                    confirmPassword = event.confirmPassword
                )
                printState()
            }

            is RegisterUiEvent.registerButtonClicked -> {
                register()
            }
        }
    }

    private fun register() {
        Log.d(TAG, "Register Button Pressed")
        printState()

        createUser(
            nama = registerUiState.value.name,
            email = registerUiState.value.email,
            password = registerUiState.value.password
        )
    }

    private fun validateData() {
        val isNameValid = Validator.validateName(
            name = registerUiState.value.name
        )

        val isEmailValid = Validator.validateEmail(
            email = registerUiState.value.email
        )

        val isPasswordValid = Validator.validatePassword(
            password = registerUiState.value.password
        )

        val isConfirmPasswordValid = Validator.validateConfirmPassword(
            confirmPassword = registerUiState.value.confirmPassword
        )

        Log.d(TAG, "Validation Data Result")
        Log.d(TAG, "Name : $isNameValid")
        Log.d(TAG, "Name : $isEmailValid")
        Log.d(TAG, "Name : $isPasswordValid")
        Log.d(TAG, "Name : $isConfirmPasswordValid")

        registerUiState.value = registerUiState.value.copy(
            nameError = isNameValid.status,
            emailError = isEmailValid.status,
            passwordError = isPasswordValid.status,
            confirmPasswordError = isConfirmPasswordValid.status
        )

        validationStatus.value = isNameValid.status && isEmailValid.status &&
                isPasswordValid.status && isConfirmPasswordValid.status

    }

    private fun printState(){
        Log.d(TAG, registerUiState.value.toString())
    }

    private fun createUser(nama: String, email: String, password: String){

        progress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Complete")
                Log.d(TAG, "${it.isSuccessful}")

                progress.value = false

                if(it.isSuccessful){
                    navHostController.navigate(Auth.Login.route)
                }
            }
            .addOnCanceledListener {
                Log.d(TAG, "Canceled")
            }
            .addOnFailureListener{
                Log.d(TAG, "Failure")
                Log.d(TAG, "${it.message}")
            }
    }

}