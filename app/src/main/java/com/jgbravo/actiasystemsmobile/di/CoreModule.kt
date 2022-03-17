package com.jgbravo.actiasystemsmobile.di

import com.jgbravo.core.timber.ActiaLogger
import com.jgbravo.core.timber.ActiaLoggerImpl
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
    fun provideActiaLogger(): ActiaLogger = ActiaLoggerImpl()
}