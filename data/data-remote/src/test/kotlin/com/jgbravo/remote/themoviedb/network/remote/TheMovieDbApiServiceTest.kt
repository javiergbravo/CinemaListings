package com.jgbravo.remote.themoviedb.network.remote

import com.google.common.truth.Truth
import com.jgbravo.remote.themoviedb.TheMovieDbApi
import com.jgbravo.remote.themoviedb.adapters.SimpleDateAdapter
import com.jgbravo.remote.utils.TheMovieDbUtils
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TheMovieDbApiServiceTest {

    private lateinit var api: TheMovieDbApi

    companion object {
        private const val DEFAULT_LANG = "es-ES"

        private lateinit var moshi: Moshi
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setUpCommon() {
            moshi = Moshi.Builder()
                .add(SimpleDateAdapter())
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(TheMovieDbUtils.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                .build()
        }
    }

    @Before
    fun setUp() {
        api = retrofit.create(TheMovieDbApi::class.java)
    }

    @Test
    fun `getAllMovies error response when no use api_key`() = runBlocking {
        val response = api.getAllMovies(page = 1, language = DEFAULT_LANG)
        Truth.assertThat(response.isSuccessful).isFalse()
    }

    @Test
    fun `getMovieDetails error response when no use api_key`() = runBlocking {
        val response = api.getMovieDetails(movieId = 1, language = DEFAULT_LANG)
        Truth.assertThat(response.isSuccessful).isFalse()
    }
}