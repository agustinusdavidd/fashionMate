package com.example.presentation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.presentation.navigation.BottomBarScreen
import com.example.presentation.navigation.Graph
import com.example.presentation.screen.calendar.CalendarScreen
import com.example.presentation.screen.profile.ProfileScreen
import com.example.presentation.screen.search.SearchScreen
import home.HomeScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(
            route = BottomBarScreen.Home.route
        ) {
            HomeScreen()
        }

        composable(
            route = BottomBarScreen.Search.route
        ) {
            SearchScreen()
        }

        composable(
            route = BottomBarScreen.Calendar.route
        ) {
            CalendarScreen()
        }

        composable(
            route = BottomBarScreen.Profile.route
        ) {
            ProfileScreen()
        }

        /*
        TODO ADD THIS CODE BELOW
        profileNavGraph(navController = navController)
         */
    }
}