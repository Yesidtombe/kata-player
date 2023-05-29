package com.dojo.globant.mycustomplayer.feature.home.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mycustomplayer.common.util.ApiResponse
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Artist
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Track
import com.dojo.globant.mycustomplayer.feature.home.domain.models.toDomain
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.GetAllArtistUseCase
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.GetFavoriteSongsUseCase
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.GetTopTracksByArtistUseCase
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.SaveFavoriteSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllArtistUseCase: GetAllArtistUseCase,
    private val getTopTracksByArtistUseCase: GetTopTracksByArtistUseCase,
    private val saveFavoriteSongUseCase: SaveFavoriteSongUseCase,
    private val getFavoriteSongsUseCase: GetFavoriteSongsUseCase
) : ViewModel() {

    private val _artistState = mutableStateListOf<Artist>()
    val artistState = _artistState
    private val _trackState = mutableStateListOf<Track>()
    val trackState = _trackState

    var indexArtistSelected: Int = 0

    init {
        viewModelScope.launch {
            getAllArtistUseCase.getArtists().collect { artistResponse ->
                when (artistResponse) {
                    is ApiResponse.Success -> {
                        _artistState.clear()
                        _artistState.addAll(artistResponse.data?.data?.map { it.toDomain() }.orEmpty())
                        getTopTracksByArtist(artistState.first(), 0)
                    }
                    is ApiResponse.Error -> {

                    }
                }
            }
        }
    }

    fun getTopTracksByArtist(artist: Artist, position: Int) {
        _artistState[indexArtistSelected] = _artistState[indexArtistSelected].copy(
            selected = false
        )
        indexArtistSelected = position
        _artistState[position] = _artistState[position].copy(
            selected = true
        )
        viewModelScope.launch {
            getTopTracksByArtistUseCase.getTopTracks(artist.id).collect { trackResponse ->
                when (trackResponse) {
                    is ApiResponse.Success -> {
                        _trackState.clear()
                        _trackState.addAll(trackResponse.data?.data?.map { it.toDomain() }.orEmpty())
                    }
                    is ApiResponse.Error -> {

                    }
                }
            }
        }
    }

    fun addFavorite(track: Track, position: Int) {
        viewModelScope.launch {
            saveFavoriteSongUseCase.saveFavoriteSong(track)
        }
        _trackState[position] = _trackState[position].copy(
            favorite = !track.favorite
        )
    }

    fun getFavorites() {
        viewModelScope.launch {
            getFavoriteSongsUseCase.getFavoritesSong().collect {
                Log.i("Favorites", it.toString())
            }
        }
    }
}