package com.dojo.globant.mycustomplayer.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.dojo.globant.mycustomplayer.R
import com.dojo.globant.mycustomplayer.feature.favorite.ui.view.FavoriteScreen
import com.dojo.globant.mycustomplayer.feature.home.ui.view.HomeScreen
import com.dojo.globant.mycustomplayer.feature.player.ui.view.PlayerScreen

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
            HomeScreen(
                paddingValues = paddingValues,
                navController = navController
            )
        }
        composable(route = Destinations.FavoritesScreen.route) {
            FavoriteScreen(
                paddingValues = paddingValues
            )
        }
        composable(route = Destinations.ProfileScreen.route) {
            Text(text = stringResource(id = R.string.settings_text_label))
        }
        detailNavGraph()
    }
}

fun NavGraphBuilder.detailNavGraph() {
    navigation(
        route = Graph.DETAILS,
        startDestination = PlayerScreen.Player.route
    ) {
        composable(route = "${PlayerScreen.Player.route}/{id}") {
            PlayerScreen(idTrack = it.arguments?.getString("id").orEmpty())
        }
    }
}

object Graph {
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}

sealed class PlayerScreen(val route: String) {
    object Player : PlayerScreen(route = "player")
}