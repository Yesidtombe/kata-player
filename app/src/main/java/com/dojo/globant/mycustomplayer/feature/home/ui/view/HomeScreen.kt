package com.dojo.globant.mycustomplayer.feature.home.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dojo.globant.mycustomplayer.common.composables.MyItemSong
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Artist
import com.dojo.globant.mycustomplayer.feature.home.ui.viewmodel.HomeViewModel
import com.dojo.globant.mycustomplayer.ui.theme.BgMainScreen
import com.dojo.globant.mycustomplayer.ui.theme.ShadowTitle
import com.dojo.globant.mycustomplayer.ui.theme.TitleWhite

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    paddingValues: PaddingValues
) {
    val artistItems = viewModel.artistState
    val trackItems = viewModel.trackState
    val artistSelected = viewModel.artistSelected
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
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
                items(artistItems) {artist ->
                    ItemArtist(artist, artistSelected) {
                        viewModel.getTopTracksByArtist(artist)
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Divider(thickness = 1.dp, color = TitleWhite)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
               verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                itemsIndexed(trackItems) { index, track ->
                    viewModel.isFavoriteTrack(track, index)
                    MyItemSong(track, {
                        viewModel.goToPlaying(it.toString(), navController)
                    }) {
                        viewModel.addFavorite(context, track, index)
                    }
                }
            }
        }
    }
}

@Composable
fun ItemArtist(artist: Artist, selected: Int, onClickArtist: () -> Unit) {
    TextButton(
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (artist.id == selected) ShadowTitle else Color.Transparent
        ),
        onClick = { onClickArtist() }
    ) {
        Text(
            text = artist.name
        )
    }
}