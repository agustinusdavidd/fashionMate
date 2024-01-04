package com.example.presentation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.presentation.data.Profile.ProfileViewModel
import com.example.presentation.navigation.Graph
import com.example.presentation.navigation.SettingsScreen
import com.example.presentation.screen.setting.SettingScreen
import com.example.presentation.screen.setting.option.ChangePasswordScreen
import com.example.presentation.screen.setting.option.DeleteAccountScreen
import com.example.presentation.screen.setting.option.EditProfileScreen
import com.example.presentation.screen.setting.option.NotificationSettingScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.SETTING,
        startDestination = SettingsScreen.Setting.route
    ) {

        val viewModel = ProfileViewModel()

//        val navController: NavHostController = rememberNavController()
        composable(
            route = SettingsScreen.Setting.route
        ) {
            SettingScreen(navController)
        }

        composable(
            route = SettingsScreen.EditProfile.route
        ) {
            EditProfileScreen(navController, viewModel)
        }

        composable(
            route = SettingsScreen.Notification.route
        ) {
            NotificationSettingScreen(navController)
        }

        composable(
            route = SettingsScreen.DeleteAccount.route
        ) {
            DeleteAccountScreen(navController)
        }

        composable(
            route = SettingsScreen.ChangePassword.route
        ) {
            ChangePasswordScreen(navController)
        }
    }
}