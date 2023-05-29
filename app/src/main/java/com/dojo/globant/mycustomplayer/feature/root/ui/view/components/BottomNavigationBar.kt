package com.dojo.globant.mycustomplayer.feature.root.ui.view.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dojo.globant.mycustomplayer.core.navigation.Destinations

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<Destinations>,
    currentRoute: String?
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = items.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation(backgroundColor = Color.Blue) {
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = { Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title.asString(),
                        tint = Color.White
                    ) },
                    label = { Text(
                        text = screen.title.asString(),
                        color = Color.White
                    ) },
                    selected = currentRoute == screen.route,
                    onClick = { /* TODO */},
                    alwaysShowLabel = false
                )
            }
        }
    }
}