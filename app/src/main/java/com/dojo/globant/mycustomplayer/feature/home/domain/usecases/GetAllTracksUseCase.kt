package com.dojo.globant.mycustomplayer.feature.home.domain.usecases

import com.dojo.globant.mycustomplayer.common.util.ApiResponse
import com.dojo.globant.mycustomplayer.feature.home.data.models.ArtistResponse
import com.dojo.globant.mycustomplayer.feature.home.data.repositories.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAllTracksUseCase(
    private val repository: HomeRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getArtists(): Flow<ApiResponse<List<ArtistResponse>>> =
        flow {
            emit(repository.getArtists())
        }.flowOn(dispatcher)
}