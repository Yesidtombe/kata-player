package com.dojo.globant.mycustomplayer.feature.home.di

import com.dojo.globant.mycustomplayer.core.datastore.UserManager
import com.dojo.globant.mycustomplayer.feature.home.data.network.ArtistClient
import com.dojo.globant.mycustomplayer.feature.home.data.network.TrackClient
import com.dojo.globant.mycustomplayer.feature.home.data.repositories.HomeRepository
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.GetAllArtistUseCase
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.GetFavoriteSongsUseCase
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.GetTopTracksByArtistUseCase
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.SaveFavoriteSongUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
object HomeModule {

    @Provides
    fun provideTrackClient(retrofit: Retrofit): TrackClient =
        retrofit.create(TrackClient::class.java)

    @Provides
    fun provideGetTopTracksByArtistUseCase(repository: HomeRepository): GetTopTracksByArtistUseCase =
        GetTopTracksByArtistUseCase(repository, Dispatchers.IO)

    @Provides
    fun provideArtistClient(retrofit: Retrofit): ArtistClient =
        retrofit.create(ArtistClient::class.java)

    @Provides
    fun provideGetAllArtistsUseCase(repository: HomeRepository): GetAllArtistUseCase =
        GetAllArtistUseCase(repository, Dispatchers.IO)

    @Provides
    fun provideSaveFavoriteSongUseCase(repository: HomeRepository): SaveFavoriteSongUseCase =
        SaveFavoriteSongUseCase(repository)

    @Provides
    fun provideGetFavoriteSongsUseCase(repository: HomeRepository): GetFavoriteSongsUseCase =
        GetFavoriteSongsUseCase(repository, Dispatchers.IO)

    @Provides
    fun provideHomeRepository(artistClient: ArtistClient, trackClient: TrackClient, userManager: UserManager): HomeRepository =
        HomeRepository(artistClient, trackClient, userManager)
}