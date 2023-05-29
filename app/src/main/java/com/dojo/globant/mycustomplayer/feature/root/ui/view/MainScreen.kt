package com.dojo.globant.mycustomplayer.feature.root.ui.view

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dojo.globant.mycustomplayer.core.navigation.Destinations
import com.dojo.globant.mycustomplayer.core.navigation.HomeNavGraph
import com.dojo.globant.mycustomplayer.feature.root.ui.view.components.BottomNavigationBar

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    val navigationItems = listOf(
        Destinations.HomeScreen,
        Destinations.FavoritesScreen,
        Destinations.ProfileScreen
    )
    val currentRoute = currentRoute(navController = navController)

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = navigationItems,
                currentRoute = currentRoute
            )
        }
    ) {
        HomeNavGraph(navController = navController, paddingValues = it)
    }
}

@Composable
private fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}