package com.jgbravo.domain.useCases

import com.jgbravo.commons.models.wrappers.Resource
import com.jgbravo.domain.models.MovieDetailsDomainModel
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsUseCase {

    operator fun invoke(movieId: Int, lang: String? = null): Flow<Resource<MovieDetailsDomainModel>>
}