package com.jgbravo.data.di

import com.jgbravo.core.timber.ActiaLogger
import com.jgbravo.data.BuildConfig
import com.jgbravo.data.remote.omdb.OmdbApi
import com.jgbravo.data.remote.omdb.interceptors.OmdbInterceptor
import com.jgbravo.data.utils.Constants
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OmdbModule {

    @Singleton
    @Provides
    fun providesOmdbInterceptor(): OmdbInterceptor = OmdbInterceptor()

    @Singleton
    @Provides
    fun provideOmdbOkHttpClient(
        omdbInterceptor: OmdbInterceptor,
        logger: ActiaLogger
    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
            .addNetworkInterceptor(omdbInterceptor)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor { message ->
                logger.http(message)
            }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            okHttpClient.addInterceptor(loggingInterceptor)
        }

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideOmdbApi(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): OmdbApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(OmdbApi::class.java)
    }
}