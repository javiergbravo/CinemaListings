package com.jgbravo.data.remote.omdb.interceptors

import com.jgbravo.data.BuildConfig
import com.jgbravo.data.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class OmdbInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url
            .newBuilder()
            .addQueryParameter(Constants.Parameters.APIKEY, BuildConfig.OMDB_API_KEY)
            .build()

        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(url)
                .build()
        )
    }
}