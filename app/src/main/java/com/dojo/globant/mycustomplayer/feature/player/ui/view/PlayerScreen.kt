package com.dojo.globant.mycustomplayer.feature.player.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dojo.globant.mycustomplayer.R
import com.dojo.globant.mycustomplayer.common.util.toMinSecTime
import com.dojo.globant.mycustomplayer.feature.player.ui.viewmodel.PlayerViewModel
import com.dojo.globant.mycustomplayer.ui.theme.*
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel = hiltViewModel(),
    idTrack: String
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getDataTrack(idTrack.toLong())
    }
    val track = viewModel.trackState
    val isPlaying = viewModel.playing
    val duration = viewModel.duration
    val progress = viewModel.progress

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgMainScreen)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .weight(6f)
                .padding(24.dp)
                .fillMaxSize()
                .aspectRatio(1f),
            colors = CardDefaults.cardColors(
                containerColor = ShadowTitle
            )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(track.value?.album?.cover)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.icon_image_loading),
                error = painterResource(R.drawable.icon_broken_image),
                contentDescription = stringResource(R.string.content_description_image_song),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Column(
            modifier = Modifier
                .weight(4f)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = track.value?.title.orEmpty(),
                    style = MyPlayerTypography.body1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    maxLines = 1
                )
                Text(
                    text = track.value?.artist?.name.orEmpty(),
                    color = ArtistGray,
                    fontSize = 20.sp,
                    maxLines = 1
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.icon_skip_previous),
                    tint = TitleWhite,
                    contentDescription = null
                )
                Icon(
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            if (isPlaying.value)
                                viewModel.pauseTrack()
                            else
                                viewModel.playTrack()
                        },
                    painter = painterResource(
                        id =
                        if (isPlaying.value)
                            R.drawable.icon_pause_circle
                        else
                            R.drawable.icon_play_circle
                    ),
                    tint = TitleWhite,
                    contentDescription = null
                )
                Icon(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.icon_skip_next),
                    tint = TitleWhite,
                    contentDescription = null
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${progress.value.toMinSecTime().first}:${progress.value.toMinSecTime().second}",
                        color = TitleWhite
                    )
                }
                Box(
                    modifier = Modifier.weight(8f),
                    contentAlignment = Alignment.Center
                ) {
                    Slider(
                        valueRange = 0F..duration.value,
                        value = progress.value,
                        onValueChange = { }
                    )
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text(
                        text = "${duration.value.toMinSecTime().first}:${duration.value.toMinSecTime().second}",
                        color = TitleWhite
                    )
                }
            }
        }
    }
    if (isPlaying.value) {
        LaunchedEffect(Unit) {
            while(true) {
                viewModel.getCurrentPosition()
                delay(1.seconds / 30)
            }
        }
    }
}