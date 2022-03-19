package com.jgbravo.data.remote.themoviedb

import com.jgbravo.data.remote.themoviedb.models.BillboardDTO
import com.jgbravo.data.utils.TheMovieDb.Endpoints
import com.jgbravo.data.utils.TheMovieDb.Parameters
import com.jgbravo.data.utils.TheMovieDb.SPANISH_LANGUAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbApi {

    @GET(Endpoints.v3_DISCOVER_MOVIE)
    suspend fun getAllMovies(
        @Query(Parameters.LANGUAGE) language: String = SPANISH_LANGUAGE,
        @Query(Parameters.PAGE) page: Int = 1
    ): Response<BillboardDTO>
}