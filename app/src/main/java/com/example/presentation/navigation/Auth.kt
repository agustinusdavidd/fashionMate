package com.example.presentation.navigation

sealed class Auth(val route: String){
    object StartPage: Auth("StartPage")
    object Login: Auth("Login")
    object Register: Auth("Register")

    /*
    * ToDo add Forgot
    */
}
