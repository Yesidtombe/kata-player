package com.dojo.globant.mycustomplayer.feature.home.domain.models

import com.dojo.globant.mycustomplayer.feature.home.data.models.Track

data class Track(
    val id: Long,
    val title: String,
    val duration: Int,
    val artist: Artist,
    val album: Album,
    val preview: String
)

fun Track.toDomain() = Track(
    id = id,
    title = title,
    duration = duration,
    artist = artist.toDomain(),
    album = album.toDomain(),
    preview = preview
)