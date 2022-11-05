package com.jgbravo.cinemalistings.di

import com.jgbravo.cinemalistings.utils.timber.CinemaListingsLoggerImpl
import com.jgbravo.commons.timber.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLogger(): Logger = CinemaListingsLoggerImpl()
}