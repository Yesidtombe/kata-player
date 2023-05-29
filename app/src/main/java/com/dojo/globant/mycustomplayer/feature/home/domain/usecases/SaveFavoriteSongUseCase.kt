package com.dojo.globant.mycustomplayer.feature.home.domain.usecases

import com.dojo.globant.mycustomplayer.feature.home.data.repositories.HomeRepository
import javax.inject.Inject

class SaveFavoriteSongUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    suspend fun saveFavoriteSong(idTrack: String) =
        repository.saveFavoriteSong(idTrack)
}