package com.example.thefirst.app_routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thefirst.activities.HomeActivity

@Composable
fun AppNavigatorHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigatorRoutes.Home.name
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = NavigatorRoutes.Home.name) {
            HomeActivity()
        }
    }
}