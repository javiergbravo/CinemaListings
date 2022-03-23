package com.jgbravo.domain.useCases

import com.jgbravo.commons.extensions.mapList
import com.jgbravo.commons.models.Resource
import com.jgbravo.data.repository.MoviesRepository
import com.jgbravo.data.repository.models.BillboardDataModel
import com.jgbravo.domain.models.MovieDomainModel
import com.jgbravo.domain.models.mappers.MovieDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(page: Int, lang: String? = null): Flow<Resource<List<MovieDomainModel>>> = flow {
        emit(Resource.Loading)
        moviesRepository.getBillboard(page, lang).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    val movieList = (resource.data as BillboardDataModel).movieList
                        .mapList(
                            mapper = MovieDomainMapper(),
                            canDiscard = true
                        )
                    emit(Resource.Success(movieList))
                }
                is Resource.Error -> emit(Resource.Error(resource.code, resource.exception))
                else -> Unit
            }
        }
    }
}