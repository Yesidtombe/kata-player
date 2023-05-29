package com.dojo.globant.mycustomplayer.feature.home.domain.usecases

import com.dojo.globant.mycustomplayer.common.util.ApiResponse
import com.dojo.globant.mycustomplayer.feature.home.data.models.ArtistResponse
import com.dojo.globant.mycustomplayer.feature.home.data.repositories.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllArtistUseCase @Inject constructor (
    private val repository: HomeRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getArtists(): Flow<ApiResponse<ArtistResponse>> =
        flow {
            emit(repository.getArtists())
        }.flowOn(dispatcher)
}