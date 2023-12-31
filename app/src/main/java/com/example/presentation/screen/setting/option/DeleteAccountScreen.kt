package com.example.presentation.screen.setting.option

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.presentation.component.SettingHeader

@Composable
fun DeleteAccountScreen(navController: NavHostController) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
    ) {
        SettingHeader(title = "Notifikasi", navController = navController)

        Spacer(modifier = Modifier.weight(0.5f))

        Column(modifier = Modifier
            .fillMaxWidth(1f)
            .align(CenterHorizontally)
        ) {
            Text(
                text = "Delete your account means you won’t be able to access your account back. And all your account data will be deleted.",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(0.5f))

        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Button(onClick = {

            },
                modifier = Modifier.height(52.dp)
            ) {
                Text(text = "Hapus Akun")
            }
        }
    }

}