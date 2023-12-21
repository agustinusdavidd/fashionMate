package com.example.presentation.screen.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.component.CustomTabs
import com.example.presentation.ui.theme.Black

@Composable
fun ProfileScreen() {

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
        .padding(top = 24.dp)
    ){

        Spacer(modifier = Modifier.weight(0.25f))

        CustomTabs()

        Spacer(modifier = Modifier.weight(0.25f))

        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(30.dp),
            imageVector = Icons.Filled.Dehaze,
            contentDescription = "Setting Icon",
            tint = Black
        )
    }

}