package com.jgbravo.data.di

import com.jgbravo.data.remote.themoviedb.TheMovieDbApi
import com.jgbravo.data.repository.MoviesRepository
import com.jgbravo.data.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun providesMoviesRepository(
        theMoviesDbApi: TheMovieDbApi
    ): MoviesRepository = MoviesRepositoryImpl(theMoviesDbApi)
}