package com.jgbravo.domain.useCases

import com.jgbravo.commons.extensions.mapList
import com.jgbravo.commons.models.Resource
import com.jgbravo.data.repository.MoviesRepository
import com.jgbravo.data.repository.models.BillboardDataModel
import com.jgbravo.domain.models.MovieDomainModel
import com.jgbravo.domain.models.mappers.MovieDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetMoviesUseCase {

    override fun invoke(page: Int, lang: String?): Flow<Resource<List<MovieDomainModel>>> = flow {
        moviesRepository.getBillboard(page, lang).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    val movieList = (resource.data as BillboardDataModel).movieList
                        .mapList(mapper = MovieDomainMapper())
                    emit(Resource.Success(movieList))
                }
                is Resource.Error -> emit(Resource.Error(resource.code, resource.exception))
            }
        }
    }
}