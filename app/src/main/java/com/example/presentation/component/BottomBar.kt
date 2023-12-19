package com.example.presentation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.presentation.navigation.BottomBarScreen
import com.example.presentation.ui.theme.White

@Composable
fun BottomBar(navController: NavHostController) {

    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Search,
        BottomBarScreen.Calendar,
        BottomBarScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDest = navBackStackEntry?.destination

    val bottomBarDestination = screens.any {
        it.route == currentDest?.route
    }

    if(bottomBarDestination) {
        BottomNavigation (
            modifier = Modifier
                .heightIn(40.dp)
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp
                    )
                ),
            backgroundColor = Color.Black
        ){
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDest = currentDest,
                    navController = navController
                )
            }
        }
    }
}
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDest: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            if(currentDest?.hierarchy?.any {
                    it.route == screen.route
                } == true){
                Text(
                    text = screen.title,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    color = White
                )
            } else {
                Text(
                    text = screen.title,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    color = Color.Gray
                )
            }
        },
        icon = {
            if(currentDest?.hierarchy?.any {
                    it.route == screen.route
                } == true){
                Icon(
                    imageVector = screen.selectedIcon,
                    contentDescription = "Navigation Icon",
                    tint = White
                )
            } else {
                Icon(
                    imageVector = screen.unselectedIcon,
                    contentDescription = "Navigation Icon",
                    tint = Color.Gray
                )
            }
        },
        selected = currentDest?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            Log.d("BottomBar", screen.route.toString())
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
