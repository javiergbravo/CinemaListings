package com.jgbravo.presentation.di

import android.content.Context
import com.jgbravo.presentation.managers.ThemeManager
import com.jgbravo.presentation.managers.ThemeManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Singleton
    @Provides
    fun provideThemeSettings(
        @ApplicationContext context: Context
    ): ThemeManager = ThemeManagerImpl(context)
}