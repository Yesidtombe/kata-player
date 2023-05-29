package com.dojo.globant.mycustomplayer.feature.home.domain.models

import com.dojo.globant.mycustomplayer.feature.home.data.models.Artist

data class Artist(
    val id: Int,
    val name: String,
    val picture: String?,
    val trackList: String,
    var selected: Boolean = false
)

fun Artist.toDomain() = Artist(
    id = id,
    name = name,
    picture = picture,
    trackList = trackList
)