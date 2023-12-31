package com.example.presentation.screen.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.presentation.component.BottomBar
import com.example.presentation.graphs.HomeNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController : NavHostController = rememberNavController()) {
    
    Scaffold (
        bottomBar = {
            BottomBar(navController = navController)
        }
    ){
        HomeNavGraph(navController = navController, modifier = Modifier.padding(bottom = 55.dp))
    }
}