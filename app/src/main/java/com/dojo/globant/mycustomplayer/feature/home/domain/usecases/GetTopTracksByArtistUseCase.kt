package com.dojo.globant.mycustomplayer.feature.home.domain.usecases

import com.dojo.globant.mycustomplayer.common.util.ApiResponse
import com.dojo.globant.mycustomplayer.feature.home.data.models.TrackResponse
import com.dojo.globant.mycustomplayer.feature.home.data.repositories.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTopTracksByArtistUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getTopTracks(artistId: Int): Flow<ApiResponse<TrackResponse>> =
        flow {
            emit(repository.getTopTracks(artistId))
        }.flowOn(dispatcher)
}