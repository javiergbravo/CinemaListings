package com.jgbravo.remote.themoviedb.network.mock

import com.google.common.truth.Truth
import com.jgbravo.commons.exceptions.AppCodeException
import com.jgbravo.commons.models.wrappers.ApiResponse
import com.jgbravo.core.data.remote.network.MockApiTest
import com.jgbravo.core.data.remote.network.MockStatus
import com.jgbravo.remote.themoviedb.TheMovieDbApi
import com.jgbravo.remote.themoviedb.adapters.SimpleDateAdapter
import com.jgbravo.remote.themoviedb.models.BillboardDTO
import com.jgbravo.remote.themoviedb.models.MovieDetailsDTO
import com.jgbravo.remote.utils.ResponseConverter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
class TheMovieDbMockApiTest : MockApiTest<TheMovieDbApi>() {

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

    @Test
    fun getAllMoviesPaginated() = runTest {
        enqueueResponse(fileRoute = "api-response/success/DiscoverMovie.json", status = MockStatus.Success)
        val response = api.getAllMovies(page = 1, language = "es_ES")
        val apiResponse = ResponseConverter.convertToApiResponse(response)

        Truth.assertThat(apiResponse).isInstanceOf(ApiResponse.Success::class.java)
        Truth.assertThat((apiResponse as ApiResponse.Success<*>).data).isInstanceOf(BillboardDTO::class.java)
    }

    @Test
    fun getMovieDetails() = runTest {
        enqueueResponse(fileRoute = "api-response/success/MovieDetails.json", status = MockStatus.Success)
        val response = api.getMovieDetails(movieId = 634649, language = "es_ES")
        val apiResponse = ResponseConverter.convertToApiResponse(response)

        Truth.assertThat(apiResponse).isInstanceOf(ApiResponse.Success::class.java)
        Truth.assertThat((apiResponse as ApiResponse.Success<*>).data).isInstanceOf(MovieDetailsDTO::class.java)

        val title = (apiResponse.data as MovieDetailsDTO).title
        Truth.assertThat(title).isEqualTo("Spider-Man: No Way Home")
    }

    @Test
    fun `getMovieDetails with no ID`() = runTest {
        enqueueResponse(fileRoute = "api-response/error/ResourceNotFound.json", status = MockStatus.Error(404))
        val response = api.getMovieDetails(movieId = -1, language = "es_ES")
        val apiResponse = ResponseConverter.convertToApiResponse(response)

        Truth.assertThat(apiResponse).isInstanceOf(ApiResponse.Error::class.java)

        val responseError = (apiResponse as ApiResponse.Error).code
        Truth.assertThat(responseError).isInstanceOf(AppCodeException.HTTP_NOT_FOUND::class.java)
    }

    @Test
    fun `getMovieDetails with invalid API key`() = runTest {
        enqueueResponse(fileRoute = "api-response/error/InvalidApiKey.json", status = MockStatus.Error(401))
        val response = api.getMovieDetails(movieId = 634649, language = "es_ES")
        val apiResponse = ResponseConverter.convertToApiResponse(response)

        Truth.assertThat(apiResponse).isInstanceOf(ApiResponse.Error::class.java)

        val responseError = (apiResponse as ApiResponse.Error).code
        Truth.assertThat(responseError).isInstanceOf(AppCodeException.HTTP_UNAUTHORIZED::class.java)
    }
}