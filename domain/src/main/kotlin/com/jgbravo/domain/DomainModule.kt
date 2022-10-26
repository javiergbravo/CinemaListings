package com.jgbravo.domain

import com.jgbravo.data.repository.MoviesRepository
import com.jgbravo.domain.useCases.GetMovieDetailsUseCase
import com.jgbravo.domain.useCases.GetMovieDetailsUseCaseImpl
import com.jgbravo.domain.useCases.GetMoviesUseCase
import com.jgbravo.domain.useCases.GetMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @ViewModelScoped
    @Provides
    fun provideGetMoviesUseCase(
        moviesRepository: MoviesRepository
    ): GetMoviesUseCase = GetMoviesUseCaseImpl(moviesRepository)

    @ViewModelScoped
    @Provides
    fun provideGetMovieDetailsUseCase(
        moviesRepository: MoviesRepository
    ): GetMovieDetailsUseCase = GetMovieDetailsUseCaseImpl(moviesRepository)
}