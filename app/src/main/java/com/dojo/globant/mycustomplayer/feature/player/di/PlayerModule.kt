package com.dojo.globant.mycustomplayer.feature.player.di

import com.dojo.globant.mycustomplayer.feature.home.data.network.TrackClient
import com.dojo.globant.mycustomplayer.feature.player.data.repositories.PlayerRepository
import com.dojo.globant.mycustomplayer.feature.player.domain.GetDataTrackUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(ViewModelComponent::class)
@Module
object PlayerModule {

    @Provides
    fun provideGetDataTrackUseCase(repository: PlayerRepository): GetDataTrackUseCase =
        GetDataTrackUseCase(repository, Dispatchers.IO)

    @Provides
    fun providePlayerRepository(trackClient: TrackClient): PlayerRepository =
        PlayerRepository(trackClient)

}