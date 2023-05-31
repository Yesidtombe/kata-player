package com.dojo.globant.mycustomplayer.feature.home.ui

import com.dojo.globant.mycustomplayer.feature.home.data.models.Track

data class TrackUiState(
    val id: Long = 0,
    val title: String = "",
    val artist: String = "",
    val cover: String = "",
    val preview: String = "",
    val favorite: Boolean = false
)

fun Track.toModel() = TrackUiState(
    id = id,
    title = title,
    artist = artist.name,
    cover = album.cover,
    preview = preview
)