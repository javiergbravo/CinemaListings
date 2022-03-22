package com.jgbravo.core.di

import android.content.Context
import com.jgbravo.core.presentation.managers.LoaderManager
import com.jgbravo.core.presentation.managers.ThemeManager
import com.jgbravo.core.presentation.managers.ThemeManagerImpl
import com.jgbravo.core.timber.ActiaLogger
import com.jgbravo.core.timber.ActiaLoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideLogger(): ActiaLogger = ActiaLoggerImpl()

    @Singleton
    @Provides
    fun provideThemeSettings(
        @ApplicationContext context: Context
    ): ThemeManager = ThemeManagerImpl(context)

    @Singleton
    @Provides
    fun provideLoaderManager(): LoaderManager = LoaderManager()
}