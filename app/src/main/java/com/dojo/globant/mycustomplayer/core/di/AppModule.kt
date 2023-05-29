package com.dojo.globant.mycustomplayer.core.di

import android.content.Context
import com.dojo.globant.mycustomplayer.core.datastore.UserManager
import com.dojo.globant.mycustomplayer.core.network.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideRetrofit() = RetrofitBuilder().retrofit()

    @Singleton
    @Provides
    fun provideUserManager(@ApplicationContext context: Context) =
        UserManager(context)
}