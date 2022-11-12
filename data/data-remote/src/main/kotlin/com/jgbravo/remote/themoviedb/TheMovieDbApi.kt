package com.jgbravo.remote.themoviedb

import com.jgbravo.core.Api
import com.jgbravo.remote.themoviedb.models.BillboardDTO
import com.jgbravo.remote.themoviedb.models.MovieDetailsDTO
import com.jgbravo.remote.utils.TheMovieDbUtils.Endpoints
import com.jgbravo.remote.utils.TheMovieDbUtils.Parameters
import com.jgbravo.remote.utils.TheMovieDbUtils.Path.MOVIE_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi : Api {

    @GET(Endpoints.DISCOVER_MOVIE)
    suspend fun getAllMovies(
        @Query(Parameters.PAGE) page: Int = 1,
        @Query(Parameters.LANGUAGE) language: String
    ): Response<BillboardDTO>

    @GET(Endpoints.MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path(MOVIE_ID) movieId: Int,
        @Query(Parameters.LANGUAGE) language: String
    ): Response<MovieDetailsDTO>
}