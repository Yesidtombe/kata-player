package com.dojo.globant.mycustomplayer.feature.home.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dojo.globant.mycustomplayer.common.composables.MyItemSong
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Artist
import com.dojo.globant.mycustomplayer.feature.home.ui.viewmodel.HomeViewModel
import com.dojo.globant.mycustomplayer.ui.theme.BgMainScreen
import com.dojo.globant.mycustomplayer.ui.theme.ShadowTitle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    paddingValues: PaddingValues
) {
    val artistItems = viewModel.artistState
    val trackItems = viewModel.trackState

    Surface(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        color = BgMainScreen
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                itemsIndexed(artistItems) { index, artist ->
                    ItemArtist(artist, navController) {
                        viewModel.getTopTracksByArtist(artist, index)
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            LazyColumn(
               verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                itemsIndexed(trackItems) { index, track ->
                    MyItemSong(track) {
                        viewModel.addFavorite(track, index)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemArtist(artist: Artist, navController: NavController, onClickArtist: () -> Unit) {
    TextButton(
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (artist.selected) ShadowTitle else Color.Transparent
        ),
        onClick = { onClickArtist() }
    ) {
        Text(
            text = artist.name
        )
    }
}