package com.example.presentation

import android.media.MediaRouter2.RouteCallback
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    /*
    Navigation merepresentasikan screen level. Untuk navigasi perpindahan screen a ke screen lain.
    Navigation membutuhkan controller untuk memandu perpindahan screen tersebut. NavHost menjadi
    wadah untuk screen-screen yang kita miliki
     */
    NavHost(navController = navController, startDestination = Route.StartPage.route){
        /*
        Dibawah adalah kode untuk navGraph yang mengarahkan kita ke screen-screen yang kita miliki
         */
        composable(
            route = Route.StartPage.route
        ) {
            StartPage(navController)
        }

        composable(
            route = Route.Login.route
        ){
            LoginScreen(navController)
        }

        composable(
            route = Route.Register.route
        ) {
            RegisterScreen(navController)
        }

        composable(
            route = Route.Home.route
        ){
            HomeScreen(navController)
        }
    }
}