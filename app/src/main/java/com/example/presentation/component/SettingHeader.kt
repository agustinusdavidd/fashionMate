package com.example.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingHeader(modifier: Modifier = Modifier, title: String, navController: NavController) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 24.dp)
    ){
        Box {
            IconButton(modifier = Modifier,
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }

            Row (modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
                horizontalArrangement =  Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }

}