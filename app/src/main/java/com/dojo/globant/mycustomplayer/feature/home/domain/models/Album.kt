package com.dojo.globant.mycustomplayer.feature.home.domain.models

import com.dojo.globant.mycustomplayer.feature.home.data.models.Album

data class Album(
    val id: Int,
    val title: String,
    val cover: String,
    val trackList: String
)

fun Album.toDomain() = Album(
    id = id,
    title = title,
    cover = cover,
    trackList = trackList
)