package com.jgbravo.remote.themoviedb.interceptors

import com.jgbravo.data.remote.BuildConfig
import com.jgbravo.remote.utils.TheMovieDbUtils
import okhttp3.Interceptor
import okhttp3.Response

class TheMovieDbInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter(TheMovieDbUtils.Parameters.API_KEY, BuildConfig.API_KEY)
            .build()

        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(url)
                .build()
        )
    }
}