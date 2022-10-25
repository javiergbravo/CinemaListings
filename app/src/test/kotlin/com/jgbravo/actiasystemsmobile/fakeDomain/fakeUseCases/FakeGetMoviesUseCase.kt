package com.jgbravo.actiasystemsmobile.fakeDomain.fakeUseCases

import com.jgbravo.actiasystemsmobile.fakeDomain.fakeModels.FakeDomainMovies
import com.jgbravo.commons.models.Resource
import com.jgbravo.domain.models.MovieDomainModel
import com.jgbravo.domain.useCases.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetMoviesUseCase : GetMoviesUseCase {

    private val moviesPage1: Resource<List<MovieDomainModel>> = Resource.Success<List<MovieDomainModel>>(
        listOf(
            FakeDomainMovies.movie1,
            FakeDomainMovies.movie2,
            FakeDomainMovies.movie3
        )
    )
    private val moviesPage2: Resource<List<MovieDomainModel>> = Resource.Success<List<MovieDomainModel>>(
        listOf(
            FakeDomainMovies.movie4,
            FakeDomainMovies.movie5,
            FakeDomainMovies.movie6
        )
    )
    private val moviesError: Resource<List<MovieDomainModel>> = Resource.Error(exception = Exception())

    override fun invoke(
        page: Int,
        lang: String?
    ): Flow<Resource<List<MovieDomainModel>>> = flow {
        emit(
            when (page) {
                1 -> moviesPage1
                2 -> moviesPage2
                else -> moviesError
            }
        )
    }
}

