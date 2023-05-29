package com.dojo.globant.mycustomplayer.feature.favorite.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dojo.globant.mycustomplayer.common.composables.MyFavoriteSong
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Album
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Artist
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Track
import com.dojo.globant.mycustomplayer.ui.theme.BgMainScreen

@Composable
fun FavoriteScreen(
    paddingValues: PaddingValues
) {
    val track = Track(
        id = 1,
        title = "Beautiful lie",
        duration = 23,
        artist = Artist(
            id = 2,
            name = "30 Seconds to Mars",
            "http://thepicture.jpg",
            trackList = "http://tracklist"
        ),
        album = Album(
            id = 3,
            title = "An album for example",
            cover = "https://img.freepik.com/foto-gratis/paisaje-niebla-matutina-montanas-globos-aerostaticos-al-amanecer_335224-794.jpg?w=500",
            trackList = "http://tracklist"
        ),
        preview = "preview"
    )
    Surface(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        color = BgMainScreen
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(8.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(12) {
                MyFavoriteSong(track)
            }
        }
    }
}