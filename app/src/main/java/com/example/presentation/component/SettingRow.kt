package com.example.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.data.Profile.ProfileViewModel
import com.example.presentation.navigation.SettingsScreen
import com.example.presentation.ui.theme.Black

@Composable
fun SettingRow(
    id: ImageVector,
    name: String,
    navHostController: NavHostController
) {

    val settingString = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Black)) {
            pushStringAnnotation(tag = name, annotation = name)
            append(name)
        }
    }

    Row(modifier = Modifier
        .padding(top = 12.dp)
        .padding(bottom = 12.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth(0.1f)
            .align(CenterVertically)
        ) {
            IconButton(
                onClick =  {
                    when(name) {
                        "Edit Profil" -> navHostController.navigate(SettingsScreen.EditProfile.route)
                        "Notifikasi" -> navHostController.navigate(SettingsScreen.Notification.route)
                        "Ubah Password" -> navHostController.navigate(SettingsScreen.ChangePassword.route)
                        "Hapus Akun" -> navHostController.navigate(SettingsScreen.DeleteAccount.route)
                    }
                },
                modifier = Modifier
                    .size(24.dp),
            ) {
                Icon(
                    imageVector = id,
                    contentDescription = "Icon"
                )
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth(0.9f)
            .align(CenterVertically)
        ){
            ClickableText(
                modifier = Modifier
                    .padding(end = 24.dp)
                    .padding(start = 18.dp),
                text = settingString,
                onClick = {
                    when(name) {
                        "Edit Profil" -> navHostController.navigate(SettingsScreen.EditProfile.route)
                        "Notifikasi" -> navHostController.navigate(SettingsScreen.Notification.route)
                        "Ubah Password" -> navHostController.navigate(SettingsScreen.ChangePassword.route)
                        "Hapus Akun" -> navHostController.navigate(SettingsScreen.DeleteAccount.route)
                    }
                }
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth(0.4f)
            .align(CenterVertically)
        ) {
            IconButton(
                onClick = {
                    when(name) {
                        "Edit Profil" -> navHostController.navigate(SettingsScreen.EditProfile.route)
                        "Notifikasi" -> navHostController.navigate(SettingsScreen.Notification.route)
                        "Ubah Password" -> navHostController.navigate(SettingsScreen.ChangePassword.route)
                        "Hapus Akun" -> navHostController.navigate(SettingsScreen.DeleteAccount.route)
                    }
                },
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowForwardIos,
                    contentDescription = "Icon"
                )
            }
        }
    }
    
    Divider(color = Color.LightGray, thickness = 1.dp)
}

@Preview(showBackground = true)
@Composable
fun SettingRowPreview() {
    SettingRow(id = Icons.Outlined.AccountCircle, name = "Edit Profile", navHostController = rememberNavController())
}