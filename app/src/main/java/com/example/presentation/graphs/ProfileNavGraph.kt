package com.example.presentation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.presentation.navigation.BottomBarScreen
import com.example.presentation.navigation.Graph

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.SETTING,
        startDestination = BottomBarScreen.Profile.route /*TODO CHANGE TO SETTINGSSCREEN*/
    ) {

    }
}