package com.example.presentation

import Route
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.data.Login.LoginViewModel
import com.example.presentation.data.Register.RegisterViewModel
import com.example.presentation.graphs.RootNavigationGraph
import com.example.presentation.screen.DummyScreen
import com.example.presentation.screen.calendar.CalendarScreen
import com.example.presentation.screen.profile.ProfileScreen
import com.example.presentation.screen.search.SearchScreen
import com.example.presentation.ui.theme.Fashion_mateTheme
import home.HomeScreen
import login.LoginScreen
import register.RegisterScreen
import startpage.StartPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            Fashion_mateTheme {
                // A surface container using the 'background' color from the theme
                FashionMateApps()
            }
        }
    }
}
@Composable
fun FashionMateApps() {
    val navController = rememberNavController()
    RootNavigationGraph(navController = navController)
}