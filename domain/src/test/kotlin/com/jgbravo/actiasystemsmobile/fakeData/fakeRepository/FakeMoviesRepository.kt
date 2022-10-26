package com.jgbravo.actiasystemsmobile.fakeData.fakeRepository

import com.jgbravo.commons.models.Resource
import com.jgbravo.data.repository.MoviesRepository
import com.jgbravo.data.repository.models.BillboardDataModel
import com.jgbravo.data.repository.models.MovieDetailsDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeMoviesRepository : MoviesRepository {

    private val flowBillboard = MutableSharedFlow<Resource<BillboardDataModel>>()
    suspend fun emitBillboard(value: Resource<BillboardDataModel>) = flowBillboard.emit(value)

    private val flowMovieDetails = MutableSharedFlow<Resource<MovieDetailsDataModel>>()
    suspend fun emitMovieDetails(value: Resource<MovieDetailsDataModel>) = flowMovieDetails.emit(value)

    override fun getBillboard(page: Int, lang: String?): Flow<Resource<BillboardDataModel>> = flowBillboard

    override fun getMovieDetails(movieId: Int, lang: String?): Flow<Resource<MovieDetailsDataModel>> = flowMovieDetails

}