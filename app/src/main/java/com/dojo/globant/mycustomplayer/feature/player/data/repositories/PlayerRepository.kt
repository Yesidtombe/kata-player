package com.dojo.globant.mycustomplayer.feature.player.data.repositories

import com.dojo.globant.mycustomplayer.R
import com.dojo.globant.mycustomplayer.common.util.ApiResponse
import com.dojo.globant.mycustomplayer.common.util.UiText
import com.dojo.globant.mycustomplayer.feature.home.data.models.Track
import com.dojo.globant.mycustomplayer.feature.home.data.network.TrackClient
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PlayerRepository @Inject constructor(
    private val trackClient: TrackClient
) {
    suspend fun getTrackById(trackId: Long) : ApiResponse<Track> {
        val response = trackClient.getTrackById(trackId)
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