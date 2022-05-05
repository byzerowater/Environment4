package com.zerowater.environment4.di

import com.zerowater.data.source.RemoteSource
import com.zerowater.data.source.RemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteSourceModule {

    @Singleton
    @Provides
    fun providesRemoteSource(source: RemoteSourceImpl): RemoteSource {
        return source
    }
}