package com.dojo.globant.mycustomplayer.feature.player.ui.viewmodel

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.globant.mycustomplayer.common.util.ApiResponse
import com.dojo.globant.mycustomplayer.feature.home.domain.models.Track
import com.dojo.globant.mycustomplayer.feature.home.domain.models.toDomain
import com.dojo.globant.mycustomplayer.feature.player.domain.GetDataTrackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val getDataTrackUseCase: GetDataTrackUseCase
) : ViewModel() {

    private val _trackState = mutableStateOf<Track?>(null)
    val trackState: State<Track?> = _trackState

    private val _playing = mutableStateOf(true)
    val playing: State<Boolean> = _playing
    private val _duration = mutableStateOf(0F)
    val duration: State<Float> = _duration
    private val _progress = mutableStateOf(0F)
    val progress: State<Float> = _progress

    private val mediaPlayer: MediaPlayer = MediaPlayer()

    fun getDataTrack(trackId: Long) {
        viewModelScope.launch {
            getDataTrackUseCase.getTrackById(trackId).collect { trackResponse ->
                when (trackResponse) {
                    is ApiResponse.Success -> {
                        _trackState.value = trackResponse.data?.toDomain()
                        Log.i("Testing", "Data track $trackId")
                        trackState.value?.preview?.let { prepareTrack(it) }
                    }
                    is ApiResponse.Error -> {

                    }
                }
            }
        }
    }

    private fun prepareTrack(preview: String) {
        mediaPlayer.apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(preview)
            prepare()
            start()
        }
        _duration.value = mediaPlayer.duration.toFloat()
    }

    fun playTrack() {
        mediaPlayer.start()
        _playing.value = true
    }

    fun pauseTrack() {
        mediaPlayer.pause()
        _playing.value = false
    }

    fun getCurrentPosition() {
        _progress.value = mediaPlayer.currentPosition.toFloat()
    }
}