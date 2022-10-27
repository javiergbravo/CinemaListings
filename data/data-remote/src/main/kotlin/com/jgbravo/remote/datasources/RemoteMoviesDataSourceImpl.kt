package com.jgbravo.remote.datasources

import com.jgbravo.commons.models.wrappers.ApiResponse
import com.jgbravo.commons.utils.DispatcherProvider
import com.jgbravo.remote.themoviedb.TheMovieDbApi
import com.jgbravo.remote.themoviedb.models.BillboardDTO
import com.jgbravo.remote.themoviedb.models.MovieDetailsDTO
import com.jgbravo.remote.utils.ResponseConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteMoviesDataSourceImpl @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val api: TheMovieDbApi
) : RemoteMoviesDataSource {

    override fun getBillboard(page: Int, lang: String): Flow<ApiResponse<BillboardDTO>> = flow {
        val response = api.getAllMovies(page, lang)
        val apiResponse = ResponseConverter.convertToApiResponse(response)
        emit(apiResponse)
    }.flowOn(dispatchers.io)

    override fun getMovieDetails(movieId: Int, lang: String): Flow<ApiResponse<MovieDetailsDTO>> = flow {
        val response = api.getMovieDetails(movieId, lang)
        val apiResponse = ResponseConverter.convertToApiResponse(response)
        emit(apiResponse)
    }.flowOn(dispatchers.io)
}