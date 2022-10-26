package com.jgbravo.remote.di

import com.jgbravo.commons.timber.Logger
import com.jgbravo.data.remote.BuildConfig
import com.jgbravo.remote.themoviedb.TheMovieDbApi
import com.jgbravo.remote.themoviedb.adapters.SimpleDateAdapter
import com.jgbravo.remote.themoviedb.interceptors.TheMovieDbInterceptor
import com.jgbravo.remote.utils.TheMovieDbUtils
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
    fun provideTheMovieDbMoshi(): Moshi = Moshi.Builder()
        .add(SimpleDateAdapter())
        .build()

    @Singleton
    @Provides
    fun providesTheMovieDbInterceptor(): TheMovieDbInterceptor = TheMovieDbInterceptor()

    @Singleton
    @Provides
    fun provideTheMovieDbOkHttpClient(
        theMovieDbInterceptor: TheMovieDbInterceptor,
        logger: Logger
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
            .baseUrl(TheMovieDbUtils.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(TheMovieDbApi::class.java)
    }
}