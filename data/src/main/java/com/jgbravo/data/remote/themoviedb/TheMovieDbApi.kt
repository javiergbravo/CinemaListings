package com.jgbravo.data.remote.themoviedb

import com.jgbravo.data.remote.themoviedb.models.BillboardDTO
import com.jgbravo.data.utils.TheMovieDb.Endpoints
import com.jgbravo.data.utils.TheMovieDb.SPANISH_LANGUAGE
import retrofit2.Response
import retrofit2.http.GET

interface TheMovieDbApi {

    @GET(Endpoints.v3_DISCOVER_MOVIE)
    suspend fun getAllMovies(
        language: String = SPANISH_LANGUAGE,
        page: Int = 1
    ): Response<BillboardDTO>
}