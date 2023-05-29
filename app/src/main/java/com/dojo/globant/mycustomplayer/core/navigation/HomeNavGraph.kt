package com.dojo.globant.mycustomplayer.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dojo.globant.mycustomplayer.feature.home.ui.view.HomeScreen

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = Destinations.HomeScreen.route
    ) {
        composable(route = Destinations.HomeScreen.route) {
            HomeScreen(paddingValues = paddingValues)
        }
    }
}

object Graph {
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}