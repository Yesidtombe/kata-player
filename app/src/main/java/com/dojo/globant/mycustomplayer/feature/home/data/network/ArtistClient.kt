package com.dojo.globant.mycustomplayer.feature.home.data.network

import com.dojo.globant.mycustomplayer.feature.home.data.models.ArtistResponse
import retrofit2.Response
import retrofit2.http.GET

interface ArtistClient {

    @GET("genre/0/artists")
    suspend fun getArtistsAllGenres() : Response<List<ArtistResponse>>

}