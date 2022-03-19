package com.jgbravo.data.repository

import com.jgbravo.core.data.BaseRepository
import com.jgbravo.core.models.Resource
import com.jgbravo.data.remote.themoviedb.TheMovieDbApi
import com.jgbravo.data.repository.mappers.BillboardMapper
import com.jgbravo.data.repository.models.BillboardDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: TheMovieDbApi
) : BaseRepository() {

    private val language = Locale.getDefault().toLanguageTag()

    fun getBillboard(page: Int): Flow<Resource<BillboardDataModel>> = flow {
        val apiResponse = moviesService.getAllMovies(language, page)
        val resource = getDataFromResponse(apiResponse, BillboardMapper())
        emit(resource)
    }
}