package com.dojo.globant.mycustomplayer.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dojo.globant.mycustomplayer.R
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Album
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Artist
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Track
import com.dojo.globant.mycustomplayer.ui.theme.ArtistGray
import com.dojo.globant.mycustomplayer.ui.theme.BgMainScreen
import com.dojo.globant.mycustomplayer.ui.theme.MyPlayerTypography
import com.dojo.globant.mycustomplayer.ui.theme.TitleWhite

@Composable
fun MyItemSong(track: Track) {
    Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(track.album.cover)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.icon_image_loading),
            error = painterResource(R.drawable.icon_broken_image),
            contentDescription = stringResource(R.string.content_description_image_song),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .weight(1f)
        )
        Column (modifier = Modifier
            .weight(4f)
            .padding(8.dp)) {
            Text(
                text = track.title,
                style = MyPlayerTypography.body1
            )
            Row (verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(13.dp).padding(end = 4.dp),
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = ArtistGray
                )
                Text(
                    text = track.artist.name,
                    color = ArtistGray,
                    fontSize = 16.sp
                )
            }
        }
        Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null, tint = TitleWhite)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPreview() {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BgMainScreen)) {
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
                cover = "https://i.pinimg.com/originals/8b/ba/73/8bba73567972ab788c36eae11af4fb51.jpg",
                trackList = "http://tracklist"
            ),
            preview = "preview"
        )
        MyItemSong(track)
        MyItemSong(track)
    }
}