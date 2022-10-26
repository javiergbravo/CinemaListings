package com.jgbravo.domain.useCases

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.jgbravo.actiasystemsmobile.fakeData.fakeModels.FakeDataModel
import com.jgbravo.actiasystemsmobile.fakeData.fakeRepository.FakeMoviesRepository
import com.jgbravo.commons.models.Resource
import com.jgbravo.domain.models.MovieDetailsDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMovieDetailsUseCaseImplTest {

    private lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCaseImpl
    private lateinit var moviesRepository: FakeMoviesRepository

    @Before
    fun setUp() {
        moviesRepository = FakeMoviesRepository()
        getMovieDetailsUseCase = GetMovieDetailsUseCaseImpl(moviesRepository)
    }

    @Test
    fun `get movie details successfully`() = runTest {
        getMovieDetailsUseCase.invoke(1, null).test {
            moviesRepository.emitMovieDetails(Resource.Success(FakeDataModel.MovieDetails.MOVIE_DETAILS))
            val item = awaitItem()
            Truth.assertThat(item).isInstanceOf(Resource.Success::class.java)
            val successDomainResult = (item as Resource.Success).data
            Truth.assertThat(successDomainResult).isInstanceOf(MovieDetailsDomainModel::class.java)
        }
    }

    @Test
    fun `get movie details error from repository`() = runTest {
        getMovieDetailsUseCase.invoke(1, null).test {
            moviesRepository.emitMovieDetails(Resource.Error(exception = Exception()))
            val item = awaitItem()
            Truth.assertThat(item).isInstanceOf(Resource.Error::class.java)
        }
    }
}