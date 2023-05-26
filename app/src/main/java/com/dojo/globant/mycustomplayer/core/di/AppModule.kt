package com.dojo.globant.mycustomplayer.core.di

import com.dojo.globant.mycustomplayer.core.network.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideRetrofit() = RetrofitBuilder().retrofit()
}