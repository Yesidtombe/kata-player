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
import com.dojo.globant.mycustomplayer.ui.theme.ArtistGray
import com.dojo.globant.mycustomplayer.ui.theme.MyPlayerTypography
import com.dojo.globant.mycustomplayer.ui.theme.TitleWhite
import com.dojo.globant.mycustomplayer.ui.theme.bgMainScreen

@Composable
fun MyItemSong() {
    Row (horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://i.pinimg.com/originals/8b/ba/73/8bba73567972ab788c36eae11af4fb51.jpg")
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.icon_image_loading),
            error = painterResource(R.drawable.icon_broken_image),
            contentDescription = stringResource(R.string.content_description_image_song),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .weight(1f)
        )
        Column (modifier = Modifier
            .weight(4f)
            .padding(8.dp)) {
            Text(
                text = "I'm Good (Blue)",
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
                    text = "David Guetta & Bebe Rexha",
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
        .background(bgMainScreen)) {
        MyItemSong()
        MyItemSong()
    }
}