package com.example.presentation.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.presentation.component.CustomTabs
import com.example.presentation.navigation.Graph
import com.example.presentation.ui.theme.Black
import com.example.presentation.ui.theme.White

@Composable
fun ProfileScreen(navController: NavHostController) {

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
        .padding(top = 24.dp)
    ){

        Spacer(modifier = Modifier.weight(0.25f))

        CustomTabs()

        Spacer(modifier = Modifier.weight(0.25f))

        IconButton(onClick = {
                navController.navigate(Graph.SETTING)
            },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(vertical = 4.dp)
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(30.dp),
                imageVector = Icons.Filled.Dehaze,
                contentDescription = "Setting Icon",
                tint = Black,
            )
        }
    }


    Box(){
        Column(modifier = Modifier
            .padding(end = 24.dp)
            .padding(bottom = 8.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ){
            FloatingActionButton(onClick = { },
                shape = RoundedCornerShape(topStart = 24.dp, bottomEnd = 24.dp, bottomStart = 24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Upload"
                )
            }
        }
    }
}