package com.dojo.globant.mycustomplayer.feature.player.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PlayerScreen(idTrack: String) {
    Text(text = "Playing -> $idTrack")
}