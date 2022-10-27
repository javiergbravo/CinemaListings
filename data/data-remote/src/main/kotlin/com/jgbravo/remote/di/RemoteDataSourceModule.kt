package com.jgbravo.remote.di

import com.jgbravo.commons.utils.DispatcherProvider
import com.jgbravo.remote.datasources.RemoteMoviesDataSource
import com.jgbravo.remote.datasources.RemoteMoviesDataSourceImpl
import com.jgbravo.remote.themoviedb.TheMovieDbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteMoviesDataSource(
        dispatchers: DispatcherProvider,
        api: TheMovieDbApi
    ): RemoteMoviesDataSource = RemoteMoviesDataSourceImpl(
        dispatchers = dispatchers,
        api = api
    )
}