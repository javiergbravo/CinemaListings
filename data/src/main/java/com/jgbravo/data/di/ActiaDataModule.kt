package com.jgbravo.data.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ActiaDataModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}