package com.jgbravo.data.di

import com.jgbravo.commons.utils.DispatcherProvider
import com.jgbravo.data.repository.MoviesRepository
import com.jgbravo.data.repository.MoviesRepositoryImpl
import com.jgbravo.remote.datasources.RemoteMoviesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Singleton
    @Provides
    fun providesMoviesRepository(
        dispatchers: DispatcherProvider,
        remoteDataSource: RemoteMoviesDataSource
    ): MoviesRepository = MoviesRepositoryImpl(
        dispatchers = dispatchers,
        remoteDataSource = remoteDataSource
    )
}