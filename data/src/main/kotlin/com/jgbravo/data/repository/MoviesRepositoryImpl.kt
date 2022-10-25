package com.jgbravo.data.repository

import com.jgbravo.commons.models.Resource
import com.jgbravo.data.remote.themoviedb.TheMovieDbApi
import com.jgbravo.data.repository.mappers.BillboardApiMapper
import com.jgbravo.data.repository.mappers.MovieDetailsApiMapper
import com.jgbravo.data.repository.models.BillboardDataModel
import com.jgbravo.data.repository.models.MovieDetailsDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: TheMovieDbApi
) : BaseRepository(), MoviesRepository {

    private val language = Locale.getDefault().toLanguageTag()

    override fun getBillboard(page: Int, lang: String?): Flow<Resource<BillboardDataModel>> = flow {
        val apiResponse = moviesService.getAllMovies(page, lang ?: language)
        val resource = getDataFromResponse(apiResponse, BillboardApiMapper())
        emit(resource)
    }.flowOn(Dispatchers.IO)

    override fun getMovieDetails(movieId: Int, lang: String?): Flow<Resource<MovieDetailsDataModel>> = flow {
        val apiResponse = moviesService.getMovieDetails(movieId, lang ?: language)
        val resource = getDataFromResponse(apiResponse, MovieDetailsApiMapper())
        emit(resource)
    }.flowOn(Dispatchers.IO)


}