package com.jgbravo.remote.datasources

import com.jgbravo.commons.models.wrappers.ApiResponse
import com.jgbravo.remote.themoviedb.models.BillboardDTO
import com.jgbravo.remote.themoviedb.models.MovieDetailsDTO
import kotlinx.coroutines.flow.Flow

interface RemoteMoviesDataSource {

    fun getBillboard(page: Int, lang: String): Flow<ApiResponse<BillboardDTO>>

    fun getMovieDetails(movieId: Int, lang: String): Flow<ApiResponse<MovieDetailsDTO>>
}