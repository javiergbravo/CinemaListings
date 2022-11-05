package com.jgbravo.cinemalistings.utils.di

import com.jgbravo.commons.utils.DefaultDispatchers
import com.jgbravo.commons.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatchers()
}