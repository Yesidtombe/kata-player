package com.dojo.globant.mycustomplayer.feature.home.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dojo.globant.mycustomplayer.R
import com.dojo.globant.mycustomplayer.common.util.ApiResponse
import com.dojo.globant.mycustomplayer.core.navigation.PlayerScreen
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

    var artistSelected by mutableStateOf(0)
        private set

    init {
        viewModelScope.launch {
            getAllArtistUseCase.getArtists().collect { artistResponse ->
                when (artistResponse) {
                    is ApiResponse.Success -> {
                        _artistState.clear()
                        _artistState.addAll(artistResponse.data?.data?.map { it.toDomain() }.orEmpty())
                        getTopTracksByArtist(artistState.first())
                    }
                    is ApiResponse.Error -> {

                    }
                }
            }
        }
    }

    fun getTopTracksByArtist(artist: Artist) {
        artistSelected = artist.id
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

    fun addFavorite(context: Context, track: Track, position: Int) {
        viewModelScope.launch {
            saveFavoriteSongUseCase.saveFavoriteSong(track)
        }
        if (track.favorite)
            Toast.makeText(
                context,
                context.getString(R.string.message_clicked_icon_favorite, "Removed"),
                Toast.LENGTH_SHORT
            ).show()
        else
            Toast.makeText(
                context,
                context.getString(R.string.message_clicked_icon_favorite, "Added"),
                Toast.LENGTH_SHORT
            ).show()
        _trackState[position] = track.copy(
            favorite = !track.favorite
        )
    }

    fun goToPlaying(idTrack: String, navController: NavController) {
        navController.navigate("${PlayerScreen.Player.route}/$idTrack")
    }

    fun isFavoriteTrack(track: Track, position: Int) {
        viewModelScope.launch {
            getFavoriteSongsUseCase.getFavoriteTrack(track.id.toString()).collect {
                if (it) {
                    _trackState[position] = track.copy(
                        favorite = true
                    )
                }
            }
        }
    }

}