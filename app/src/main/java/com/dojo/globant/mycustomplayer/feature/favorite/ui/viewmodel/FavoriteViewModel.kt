package com.dojo.globant.mycustomplayer.feature.favorite.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Track
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.GetFavoriteSongsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteSongsUseCase: GetFavoriteSongsUseCase
) : ViewModel() {

    private val _trackState = mutableStateListOf<Track>()
    val trackState = _trackState

    init {
        viewModelScope.launch {
            getFavoriteSongsUseCase.getAllFavoritesTrack().collect {
                _trackState.clear()
                _trackState.addAll(it)
            }
        }
    }

}