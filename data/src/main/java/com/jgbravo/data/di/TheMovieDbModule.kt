package com.jgbravo.data.di

import com.jgbravo.core.timber.ActiaLogger
import com.jgbravo.data.BuildConfig
import com.jgbravo.data.remote.themoviedb.TheMovieDbApi
import com.jgbravo.data.remote.themoviedb.interceptors.TheMovieDbInterceptor
import com.jgbravo.data.utils.TheMovieDb
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
object TheMovieDbModule {

    @Singleton
    @Provides
    fun providesTheMovieDbInterceptor(): TheMovieDbInterceptor = TheMovieDbInterceptor()

    @Singleton
    @Provides
    fun provideTheMovieDbOkHttpClient(
        theMovieDbInterceptor: TheMovieDbInterceptor,
        logger: ActiaLogger
    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
            .addNetworkInterceptor(theMovieDbInterceptor)

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
    fun provideTheMovieDbApi(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): TheMovieDbApi {
        return Retrofit.Builder()
            .baseUrl(TheMovieDb.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TheMovieDbApi::class.java)
    }
}