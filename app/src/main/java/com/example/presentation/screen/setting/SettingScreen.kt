package com.example.presentation.screen.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Beenhere
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material.icons.outlined.Report
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.component.SettingHeader
import com.example.presentation.component.SettingRow
import com.example.presentation.ui.theme.Secondary

@Composable
fun SettingScreen(navController: NavHostController = rememberNavController()) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
    ){
        SettingHeader(title = "Setting", navController = navController)

        Text(
            text = "Akun",
            color = Secondary,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        SettingRow(id = Icons.Outlined.AccountCircle, name = "Edit Profil", navHostController = navController)

        SettingRow(id = Icons.Outlined.Notifications, name = "Notifikasi", navHostController = navController)

        SettingRow(id = Icons.Outlined.Key, name = "Ubah Password", navHostController = navController)

        SettingRow(id = Icons.Outlined.DeleteForever, name = "Hapus Akun", navHostController = navController)

        SettingRow(id = Icons.Outlined.Logout, name = "Keluar", navHostController = navController)

        Spacer(modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth())

        Text(
            text = "Bantuan",
            color = Secondary,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        SettingRow(id = Icons.Outlined.Report, name = "Laporkan Bug", navHostController = navController)

        SettingRow(id = Icons.Outlined.QuestionMark, name = "FAQ", navHostController = navController)

        SettingRow(id = Icons.Outlined.Beenhere, name = "Terms and Condition", navHostController = navController)
    }
}