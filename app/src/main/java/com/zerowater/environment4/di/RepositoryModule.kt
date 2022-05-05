package com.zerowater.environment4.di

import com.zerowater.data.GithubRepositoryImpl
import com.zerowater.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Singleton
    @Provides
    fun providesGithubRepository(source: GithubRepositoryImpl): GithubRepository {
        return source
    }
}