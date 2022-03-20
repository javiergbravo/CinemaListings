package com.jgbravo.data.repository

import com.jgbravo.core.models.Resource
import com.jgbravo.data.repository.models.BillboardDataModel
import com.jgbravo.data.repository.models.MovieDetailsDataModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getBillboard(page: Int, lang: String?): Flow<Resource<BillboardDataModel>>

    fun getMovieDetails(movieId: Int, lang: String?): Flow<Resource<MovieDetailsDataModel>>
}