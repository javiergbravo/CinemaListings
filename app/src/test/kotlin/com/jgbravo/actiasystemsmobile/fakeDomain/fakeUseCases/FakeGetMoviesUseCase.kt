package com.jgbravo.actiasystemsmobile.fakeDomain.fakeUseCases

import com.jgbravo.commons.models.Resource
import com.jgbravo.domain.models.MovieDomainModel
import com.jgbravo.domain.useCases.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeGetMoviesUseCase : GetMoviesUseCase {

    private val flow = MutableSharedFlow<Resource<List<MovieDomainModel>>>()
    suspend fun emit(value: Resource<List<MovieDomainModel>>) = flow.emit(value)

    override fun invoke(
        page: Int,
        lang: String?
    ): Flow<Resource<List<MovieDomainModel>>> = flow

    /*override fun invoke(
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
    }*/
}

