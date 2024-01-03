package com.example.presentation.navigation

sealed class SettingsScreen(val route: String){
    object Setting: SettingsScreen("Setting")
    object EditProfile: SettingsScreen("Edit Profile")
    object Notification: SettingsScreen("Notification")
    object DeleteAccount: SettingsScreen("Delete Account")
    object ChangePassword: SettingsScreen("Change Password")
}
