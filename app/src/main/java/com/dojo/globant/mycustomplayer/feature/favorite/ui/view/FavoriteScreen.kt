package com.dojo.globant.mycustomplayer.feature.favorite.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dojo.globant.mycustomplayer.common.composables.MyFavoriteSong
import com.dojo.globant.mycustomplayer.feature.favorite.ui.viewmodel.FavoriteViewModel
import com.dojo.globant.mycustomplayer.ui.theme.BgMainScreen

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavController,
    paddingValues: PaddingValues
) {
    val favorites = viewModel.trackState

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = BgMainScreen
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(8.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(favorites) { favorite ->
                MyFavoriteSong(favorite) {
                    viewModel.goToPlaying(it.toString(), navController)
                }
            }
        }
    }
}