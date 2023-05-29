package com.dojo.globant.mycustomplayer.feature.home.data.network

import com.dojo.globant.mycustomplayer.feature.home.data.models.TrackResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TrackClient {

    @GET("artist/{artistId}top?limit=50")
    suspend fun getTopTracksByArtis(
        @Path("artistId") artistId: Int
    ) : Response<List<TrackResponse>>

}