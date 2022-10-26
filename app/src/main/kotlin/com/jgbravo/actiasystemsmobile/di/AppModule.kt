package com.jgbravo.actiasystemsmobile.di

import com.jgbravo.actiasystemsmobile.utils.timber.ActiaLoggerImpl
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
    fun provideLogger(): Logger = ActiaLoggerImpl()
}