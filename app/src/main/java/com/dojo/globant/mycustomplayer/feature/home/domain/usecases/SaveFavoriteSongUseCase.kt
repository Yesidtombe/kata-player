package com.dojo.globant.mycustomplayer.feature.home.domain.usecases

import com.dojo.globant.mycustomplayer.feature.home.data.repositories.HomeRepository
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Track
import javax.inject.Inject

class SaveFavoriteSongUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend fun saveFavoriteSong(track: Track) {
        if (track.favorite)
            repository.deleteFavoriteTrack(track.id.toString())
        else
            repository.saveFavoriteTrack(track.id.toString())
    }
}