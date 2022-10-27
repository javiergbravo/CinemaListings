package com.jgbravo.data.repository

import com.jgbravo.commons.models.wrappers.Resource
import com.jgbravo.commons.utils.DispatcherProvider
import com.jgbravo.data.converters.ResourceConverter
import com.jgbravo.data.repository.mappers.BillboardApiMapper
import com.jgbravo.data.repository.mappers.MovieDetailsApiMapper
import com.jgbravo.data.repository.models.BillboardDataModel
import com.jgbravo.data.repository.models.MovieDetailsDataModel
import com.jgbravo.remote.datasources.RemoteMoviesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val remoteDataSource: RemoteMoviesDataSource
) : MoviesRepository {

    private val language = Locale.getDefault().toLanguageTag()

    override fun getBillboard(page: Int, lang: String?): Flow<Resource<BillboardDataModel>> =
        remoteDataSource.getBillboard(page = page, lang = lang ?: language).map {
            ResourceConverter.getDataFromApiResponse(it, BillboardApiMapper())
        }.flowOn(dispatchers.default)

    override fun getMovieDetails(movieId: Int, lang: String?): Flow<Resource<MovieDetailsDataModel>> =
        remoteDataSource.getMovieDetails(movieId = movieId, lang = lang ?: language).map {
            ResourceConverter.getDataFromApiResponse(it, MovieDetailsApiMapper())
        }.flowOn(dispatchers.default)
}