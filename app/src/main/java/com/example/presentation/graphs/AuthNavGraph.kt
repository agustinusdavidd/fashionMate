package com.example.presentation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.presentation.data.Login.LoginViewModel
import com.example.presentation.data.Register.RegisterViewModel
import com.example.presentation.navigation.Auth
import com.example.presentation.navigation.Graph
import login.LoginScreen
import register.RegisterScreen
import startpage.StartPage

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    val loginViewModel: LoginViewModel = LoginViewModel(navController)
    val registerViewModel: RegisterViewModel = RegisterViewModel(navController)

    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = Auth.StartPage.route
    ) {
        composable(
            route = Auth.StartPage.route
        ) {
            StartPage(navHostController = navController)
        }

        composable(
            route = Auth.Login.route
        ) {
            LoginScreen(loginViewModel = loginViewModel, navHostController = navController)
        }

        composable(
            route = Auth.Register.route
        ) {
            RegisterScreen(registerViewModel = registerViewModel, navHostController = navController)
        }
    }
}