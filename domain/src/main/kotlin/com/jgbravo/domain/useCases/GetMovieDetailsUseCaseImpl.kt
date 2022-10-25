package com.jgbravo.domain.useCases

import com.jgbravo.commons.models.Resource
import com.jgbravo.data.repository.MoviesRepository
import com.jgbravo.data.repository.models.MovieDetailsDataModel
import com.jgbravo.domain.models.MovieDetailsDomainModel
import com.jgbravo.domain.models.mappers.MovieDetailsDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetMovieDetailsUseCase {

    override fun invoke(movieId: Int, lang: String?): Flow<Resource<MovieDetailsDomainModel>> = flow {
        moviesRepository.getMovieDetails(movieId, lang).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    val movie = MovieDetailsDomainMapper().map((resource.data as MovieDetailsDataModel))
                    emit(Resource.Success(movie))
                }
                is Resource.Error -> emit(Resource.Error(resource.code, resource.exception))
            }
        }
    }
}