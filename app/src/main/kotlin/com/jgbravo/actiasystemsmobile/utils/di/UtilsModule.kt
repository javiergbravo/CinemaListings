package com.jgbravo.actiasystemsmobile.utils.di

import com.jgbravo.commons.utils.DefaultDispatchers
import com.jgbravo.commons.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UtilsModule {

    @ViewModelScoped
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatchers()
}