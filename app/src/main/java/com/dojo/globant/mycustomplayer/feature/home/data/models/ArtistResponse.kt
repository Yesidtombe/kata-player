package com.dojo.globant.mycustomplayer.feature.home.data.models

import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("data")
    val data: List<Artist>
)