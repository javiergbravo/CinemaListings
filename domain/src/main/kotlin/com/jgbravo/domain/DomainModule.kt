package com.jgbravo.domain

import com.jgbravo.data.repository.MoviesRepository
import com.jgbravo.domain.useCases.GetMovieDetailsUseCase
import com.jgbravo.domain.useCases.GetMovieDetailsUseCaseImpl
import com.jgbravo.domain.useCases.GetMoviesUseCase
import com.jgbravo.domain.useCases.GetMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideGetMoviesUseCase(
        moviesRepository: MoviesRepository
    ): GetMoviesUseCase = GetMoviesUseCaseImpl(moviesRepository)

    @Singleton
    @Provides
    fun provideGetMovieDetailsUseCase(
        moviesRepository: MoviesRepository
    ): GetMovieDetailsUseCase = GetMovieDetailsUseCaseImpl(moviesRepository)
}