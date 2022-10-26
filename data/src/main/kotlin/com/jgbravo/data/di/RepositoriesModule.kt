package com.jgbravo.data.di

import com.jgbravo.data.repository.MoviesRepository
import com.jgbravo.data.repository.MoviesRepositoryImpl
import com.jgbravo.remote.themoviedb.TheMovieDbApi
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
        theMoviesDbApi: TheMovieDbApi
    ): MoviesRepository = MoviesRepositoryImpl(api = theMoviesDbApi)
}