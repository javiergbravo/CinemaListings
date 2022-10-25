package com.jgbravo.presentation.di

import android.content.Context
import com.jgbravo.presentation.managers.LoaderManager
import com.jgbravo.presentation.managers.LoaderManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @ActivityScoped
    @Provides
    fun provideLoaderManager(
        @ActivityContext activityContext: Context
    ): LoaderManager = LoaderManagerImpl(activityContext)
}