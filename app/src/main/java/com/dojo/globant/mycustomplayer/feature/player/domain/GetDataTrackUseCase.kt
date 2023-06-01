package com.dojo.globant.mycustomplayer.feature.player.domain

import com.dojo.globant.mycustomplayer.common.util.ApiResponse
import com.dojo.globant.mycustomplayer.feature.home.data.models.Track
import com.dojo.globant.mycustomplayer.feature.player.data.repositories.PlayerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDataTrackUseCase @Inject constructor(
    private val repository: PlayerRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getTrackById(trackId: Long) : Flow<ApiResponse<Track>> =
        flow {
            emit(repository.getTrackById(trackId))
        }.flowOn(dispatcher)
}