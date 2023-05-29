package com.dojo.globant.mycustomplayer.feature.home.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mycustomplayer.common.util.ApiResponse
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Artist
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Track
import com.dojo.globant.mycustomplayer.feature.home.domain.models.toDomain
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.GetAllArtistUseCase
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.GetTopTracksByArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllArtistUseCase: GetAllArtistUseCase,
    private val getTopTracksByArtistUseCase: GetTopTracksByArtistUseCase
) : ViewModel() {

    private val _artistState = mutableStateOf<List<Artist>>(listOf())
    val artistState = _artistState

    private val _trackState = mutableStateOf<List<Track>>(listOf())
    val trackState = _trackState

    init {
        viewModelScope.launch {
            getAllArtistUseCase.getArtists().collect { artistResponse ->
                when (artistResponse) {
                    is ApiResponse.Success -> {
                        _artistState.value = artistResponse.data?.data?.map { it.toDomain() }.orEmpty()
                        getTopTracksByArtistUseCase.getTopTracks(artistState.value.first().id).collect { trackResponse ->
                            when (trackResponse) {
                                is ApiResponse.Success -> {
                                    _trackState.value = trackResponse.data?.data?.map { it.toDomain() }.orEmpty()
                                }
                                is ApiResponse.Error -> {

                                }
                            }
                        }
                    }
                    is ApiResponse.Error -> {

                    }
                }
            }
        }
    }

}