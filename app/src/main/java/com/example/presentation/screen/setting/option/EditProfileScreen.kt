package com.example.presentation.screen.setting.option

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.presentation.component.SettingHeader

@Composable
fun EditProfileScreen(navController: NavHostController) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
    ) {
        SettingHeader(title = "Edit Profile", navController = navController)

    }
}