package com.jgbravo.commons.di

import com.jgbravo.commons.timber.ActiaLogger
import com.jgbravo.commons.timber.ActiaLoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideLogger(): ActiaLogger = ActiaLoggerImpl()
}