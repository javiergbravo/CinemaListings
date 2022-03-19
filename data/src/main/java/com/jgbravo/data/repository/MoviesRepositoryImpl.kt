package com.jgbravo.data.repository

import com.jgbravo.core.data.BaseRepository
import com.jgbravo.core.models.Resource
import com.jgbravo.data.remote.themoviedb.TheMovieDbApi
import com.jgbravo.data.repository.mappers.BillboardApiMapper
import com.jgbravo.data.repository.models.BillboardDataModel
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

    override fun getBillboard(page: Int): Flow<Resource<BillboardDataModel>> = flow {
        val apiResponse = moviesService.getAllMovies(language, page)
        val resource = getDataFromResponse(apiResponse, BillboardApiMapper())
        emit(resource)
    }.flowOn(Dispatchers.IO)
}