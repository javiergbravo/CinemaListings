package com.jgbravo.commons.di

import com.jgbravo.commons.timber.AppLogger
import com.jgbravo.commons.timber.AppLoggerImpl
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
    fun provideLogger(): AppLogger = AppLoggerImpl()
}