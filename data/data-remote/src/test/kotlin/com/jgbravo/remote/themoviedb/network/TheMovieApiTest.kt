package com.jgbravo.remote.themoviedb.network

import com.google.common.truth.Truth
import com.jgbravo.commons.exceptions.ApiCallException
import com.jgbravo.commons.models.wrappers.ApiResponse
import com.jgbravo.core.data.remote.network.MockApiTest
import com.jgbravo.remote.themoviedb.TheMovieDbApi
import com.jgbravo.remote.themoviedb.adapters.SimpleDateAdapter
import com.jgbravo.remote.themoviedb.models.BillboardDTO
import com.jgbravo.remote.utils.ResponseConverter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class TheMovieApiTest : MockApiTest<TheMovieDbApi>() {

    private lateinit var api: TheMovieDbApi
    private lateinit var moshi: Moshi

    override fun createApi(): TheMovieDbApi {
        val moshi: Moshi = Moshi.Builder()
            .add(SimpleDateAdapter())
            .build()

        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(TheMovieDbApi::class.java)
    }

    @Before
    fun initApi() {
        moshi = Moshi.Builder()
            .add(SimpleDateAdapter())
            .build()
        api = createApi()
    }

    @Throws(ApiCallException::class)
    @Test
    fun getAllMoviesPaginated() = runTest {
        enqueueResponse("api-response/success/DiscoverMovie.json")
        val response = api.getAllMovies(page = 1, language = "es_ES")
        val apiResponse = ResponseConverter.convertToApiResponse(response)

        Truth.assertThat(apiResponse).isInstanceOf(ApiResponse.Success::class.java)
        Truth.assertThat((apiResponse as ApiResponse.Success<*>).data).isInstanceOf(BillboardDTO::class.java)
    }
}