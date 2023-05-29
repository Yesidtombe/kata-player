package com.dojo.globant.mycustomplayer.feature.home.di

import com.dojo.globant.mycustomplayer.feature.home.data.network.ArtistClient
import com.dojo.globant.mycustomplayer.feature.home.data.repositories.HomeRepository
import com.dojo.globant.mycustomplayer.feature.home.domain.usecases.GetAllTracksUseCase
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
    fun provideArtistClient(retrofit: Retrofit): ArtistClient =
        retrofit.create(ArtistClient::class.java)

    @Provides
    fun provideGetAllTracksUseCase(repository: HomeRepository): GetAllTracksUseCase =
        GetAllTracksUseCase(repository, Dispatchers.IO)

    fun provideHomeRepository(artistClient: ArtistClient): HomeRepository =
        HomeRepository(artistClient)
}