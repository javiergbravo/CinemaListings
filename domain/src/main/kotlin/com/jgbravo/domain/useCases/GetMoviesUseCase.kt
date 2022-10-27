package com.jgbravo.domain.useCases

import com.jgbravo.commons.models.wrappers.Resource
import com.jgbravo.domain.models.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {

    operator fun invoke(page: Int, lang: String? = null): Flow<Resource<List<MovieDomainModel>>>
}