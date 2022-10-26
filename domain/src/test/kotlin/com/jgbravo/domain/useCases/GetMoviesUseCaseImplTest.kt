package com.jgbravo.domain.useCases

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.jgbravo.actiasystemsmobile.fakeData.fakeModels.FakeDataModel
import com.jgbravo.actiasystemsmobile.fakeData.fakeRepository.FakeMoviesRepository
import com.jgbravo.commons.models.Resource
import com.jgbravo.domain.models.MovieDomainModel
import com.jgbravo.domain.utils.getListFromFirstSuccessResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMoviesUseCaseImplTest {

    private lateinit var getMoviesUseCase: GetMoviesUseCaseImpl
    private lateinit var moviesRepository: FakeMoviesRepository

    @Before
    fun setUp() {
        moviesRepository = FakeMoviesRepository()
        getMoviesUseCase = GetMoviesUseCaseImpl(
            moviesRepository = moviesRepository
        )
    }

    @Test
    fun `get billboard one page successfully`() = runTest {
        val values = mutableListOf<Resource<List<MovieDomainModel>>>()
        val collectJob = launch(UnconfinedTestDispatcher()) {
            getMoviesUseCase.invoke(1, null).toList(values)
        }

        moviesRepository.emitBillboard(Resource.Success(FakeDataModel.BillboardModel.BILLBOARD_PAGE_1))
        val successDomainResult = values.getListFromFirstSuccessResult()
        Truth.assertThat(successDomainResult.first()).isInstanceOf(MovieDomainModel::class.java)
        Truth.assertThat(successDomainResult.size).isEqualTo(3)

        collectJob.cancel()
    }

    @Test
    fun `get billboard more pages successfully`() = runTest {
        getMoviesUseCase.invoke(1, null).test {
            moviesRepository.emitBillboard(Resource.Success(FakeDataModel.BillboardModel.BILLBOARD_PAGE_1))
            val item = awaitItem()
            Truth.assertThat(item).isInstanceOf(Resource.Success::class.java)
            val successDomainResult = (item as Resource.Success).data as List<MovieDomainModel>
            Truth.assertThat(successDomainResult.size).isEqualTo(3)
            Truth.assertThat(successDomainResult.first()).isInstanceOf(MovieDomainModel::class.java)
        }
        getMoviesUseCase.invoke(2, null).test {
            moviesRepository.emitBillboard(Resource.Success(FakeDataModel.BillboardModel.BILLBOARD_PAGE_2))
            val item = awaitItem()
            Truth.assertThat(item).isInstanceOf(Resource.Success::class.java)
            val successDomainResult = (item as Resource.Success).data as List<MovieDomainModel>
            Truth.assertThat(successDomainResult.size).isEqualTo(3)
            Truth.assertThat(successDomainResult.first()).isInstanceOf(MovieDomainModel::class.java)
        }
    }

    @Test
    fun `get billboard error from repository`() = runTest {
        getMoviesUseCase.invoke(1, null).test {
            moviesRepository.emitBillboard(Resource.Error(exception = Exception()))
            Truth.assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
        }
    }
}