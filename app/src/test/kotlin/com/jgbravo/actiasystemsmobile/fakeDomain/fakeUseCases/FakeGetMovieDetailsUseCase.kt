package com.jgbravo.actiasystemsmobile.fakeDomain.fakeUseCases

import com.jgbravo.commons.models.wrappers.Resource
import com.jgbravo.domain.models.MovieDetailsDomainModel
import com.jgbravo.domain.useCases.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeGetMovieDetailsUseCase : GetMovieDetailsUseCase {

    private val flow = MutableSharedFlow<Resource<MovieDetailsDomainModel>>()
    suspend fun emit(value: Resource<MovieDetailsDomainModel>) = flow.emit(value)

    override fun invoke(movieId: Int, lang: String?): Flow<Resource<MovieDetailsDomainModel>> = flow
}