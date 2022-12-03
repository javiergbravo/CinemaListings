package com.jgbravo.remote.themoviedb.network.mock.dispatchers

import com.google.common.truth.Truth
import com.jgbravo.commons.models.wrappers.ApiResponse
import com.jgbravo.remote.themoviedb.TheMovieDbApi
import com.jgbravo.remote.themoviedb.adapters.SimpleDateAdapter
import com.jgbravo.remote.themoviedb.models.BillboardDTO
import com.jgbravo.remote.themoviedb.models.MovieDetailsDTO
import com.jgbravo.remote.utils.ResponseConverter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class MockApiWithDispatchersTest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var api: TheMovieDbApi
    private lateinit var moshi: Moshi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockServerDispatcher.RequestDispatcher()
        mockWebServer.start()

        moshi = Moshi.Builder()
            .add(SimpleDateAdapter())
            .build()
        api = createApi()
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    private fun createApi(): TheMovieDbApi {
        val moshi: Moshi = Moshi.Builder()
            .add(SimpleDateAdapter())
            .build()

        return Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(TheMovieDbApi::class.java)
    }

    @Test
    fun getAllMoviesPaginated() = runTest {
        val response = api.getAllMovies(page = 1, language = "es_ES")
        val apiResponse = ResponseConverter.convertToApiResponse(response)

        Truth.assertThat(apiResponse).isInstanceOf(ApiResponse.Success::class.java)
        Truth.assertThat((apiResponse as ApiResponse.Success<*>).data).isInstanceOf(BillboardDTO::class.java)
    }

    @Test
    fun getMovieDetails() = runTest {
        val response = api.getMovieDetails(movieId = 634649, language = "es_ES")
        val apiResponse = ResponseConverter.convertToApiResponse(response)

        Truth.assertThat(apiResponse).isInstanceOf(ApiResponse.Success::class.java)
        Truth.assertThat((apiResponse as ApiResponse.Success<*>).data).isInstanceOf(MovieDetailsDTO::class.java)

        val title = (apiResponse.data as MovieDetailsDTO).title
        Truth.assertThat(title).isEqualTo("Spider-Man: No Way Home")
    }
}