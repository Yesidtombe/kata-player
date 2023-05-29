package com.dojo.globant.mycustomplayer.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.dojo.globant.mycustomplayer.R
import com.dojo.globant.mycustomplayer.common.util.UiText

sealed class Destinations(
    val route: String,
    val title: UiText,
    val icon: ImageVector
) {
    object HomeScreen: Destinations(
        route = "home",
        title = UiText.StringResource(id = R.string.home_text_label),
        icon = Icons.Default.Home
    )
    object FavoritesScreen: Destinations(
        route = "favorites",
        title = UiText.StringResource(id = R.string.favorite_text_label),
        icon = Icons.Default.Favorite
    )
    object ProfileScreen: Destinations(
        route = "profile",
        title = UiText.StringResource(id = R.string.profile_text_label),
        icon = Icons.Default.Person
    )
}