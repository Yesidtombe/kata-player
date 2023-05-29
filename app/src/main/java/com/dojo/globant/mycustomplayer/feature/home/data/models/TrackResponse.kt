package com.dojo.globant.mycustomplayer.feature.home.data.models

import com.google.gson.annotations.SerializedName

data class TrackResponse(
    @SerializedName("data")
    val data: List<Track>,
    @SerializedName("next")
    val next: String,
    @SerializedName("total")
    val total: Int
)