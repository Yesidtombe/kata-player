package com.dojo.globant.mycustomplayer.feature.home.data.repositories

import com.dojo.globant.mycustomplayer.R
import com.dojo.globant.mycustomplayer.common.util.ApiResponse
import com.dojo.globant.mycustomplayer.common.util.UiText
import com.dojo.globant.mycustomplayer.feature.home.data.models.ArtistResponse
import com.dojo.globant.mycustomplayer.feature.home.data.models.TrackResponse
import com.dojo.globant.mycustomplayer.feature.home.data.network.ArtistClient
import com.dojo.globant.mycustomplayer.feature.home.data.network.TrackClient
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class HomeRepository @Inject constructor(
    private val artistClient: ArtistClient,
    private val trackClient: TrackClient
) {
    suspend fun getArtists(): ApiResponse<ArtistResponse> {
        val response = artistClient.getArtistsAllGenres()
        return when {
            response.isSuccessful && response.body() != null ->
                ApiResponse.Success(data = response.body())
            response.code() == 404 ->
                ApiResponse.Error(errorMessage = UiText.StringResource(id = R.string.not_found_error))
            else ->
                ApiResponse.Error(errorMessage = UiText.StringResource(id = R.string.unexpected_error))
        }
    }

    suspend fun getTopTracks(artistId: Int): ApiResponse<TrackResponse> {
        val response = trackClient.getTopTracksByArtis(artistId)
        return when {
            response.isSuccessful && response.body() != null ->
                ApiResponse.Success(data = response.body())
            response.code() == 404 ->
                ApiResponse.Error(errorMessage = UiText.StringResource(id = R.string.not_found_error))
            else ->
                ApiResponse.Error(errorMessage = UiText.StringResource(id = R.string.unexpected_error))
        }
    }
}