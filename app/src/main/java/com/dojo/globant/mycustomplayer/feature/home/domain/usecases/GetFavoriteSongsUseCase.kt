package com.dojo.globant.mycustomplayer.feature.home.domain.usecases

import com.dojo.globant.mycustomplayer.feature.home.data.repositories.HomeRepository
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Track
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavoriteSongsUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getAllFavoritesTrack(): Flow<List<Track>> =
        flow {
            emit(repository.getAllFavoritesTrack())
        }.flowOn(dispatcher)

    suspend fun getFavoriteTrack(idTrack: String): Flow<Boolean> =
        flow {
            val favorite = repository.getFavoriteTrack(idTrack)
            if (favorite.isBlank())
                emit(false)
            else
                emit(true)
        }.flowOn(dispatcher)
}