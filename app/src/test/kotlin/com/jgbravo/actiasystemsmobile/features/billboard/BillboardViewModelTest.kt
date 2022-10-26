package com.jgbravo.actiasystemsmobile.features.billboard

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.jgbravo.actiasystemsmobile.fakeDomain.fakeModels.FakeDomain
import com.jgbravo.actiasystemsmobile.fakeDomain.fakeUseCases.FakeGetMoviesUseCase
import com.jgbravo.actiasystemsmobile.utils.TestDispatchers
import com.jgbravo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BillboardViewModelTest {

    private lateinit var viewModel: BillboardViewModel
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var getMoviesUseCase: FakeGetMoviesUseCase


    @Before
    fun setUp() {
        // Test Doubles
        testDispatchers = TestDispatchers()
        getMoviesUseCase = FakeGetMoviesUseCase()
        viewModel = BillboardViewModel(
            dispatchers = testDispatchers,
            getMoviesUseCase = getMoviesUseCase
        )
    }

    @Test
    fun `moviesState update pagination list`() = runTest {
        Truth.assertThat(viewModel.movies.value).isEmpty() // Initial value

        viewModel.getMovies()

        getMoviesUseCase.emit(FakeDomain.ResourceMoviesList.MOVIES_LIST_1)
        Truth.assertThat(viewModel.movies.value.size).isEqualTo(3)

        getMoviesUseCase.emit(FakeDomain.ResourceMoviesList.MOVIES_LIST_2)
        Truth.assertThat(viewModel.movies.value.size).isEqualTo(6)

        getMoviesUseCase.emit(FakeDomain.ResourceMoviesList.MOVIES_LIST_ERROR)
        Truth.assertThat(viewModel.movies.value.size).isEqualTo(6)
    }

    @Test
    fun `uiState update to loading`() = runTest {
        viewModel.uiState.test {
            viewModel.getMovies()

            getMoviesUseCase.emit(FakeDomain.ResourceMoviesList.MOVIES_LIST_1)
            val emissionLoading = awaitItem()
            Truth.assertThat(emissionLoading).isInstanceOf(BaseViewModel.UiState.Loading::class.java)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `uiState update to error`() = runTest {
        viewModel.uiState.test {
            viewModel.getMovies()

            getMoviesUseCase.emit(FakeDomain.ResourceMoviesList.MOVIES_LIST_ERROR)
            skipItems(1) // Skip loading state
            val emissionError = awaitItem()
            Truth.assertThat(emissionError).isInstanceOf(BaseViewModel.UiState.Error::class.java)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `uiState update to loading and then error`() = runTest {
        viewModel.uiState.test {
            viewModel.getMovies()

            getMoviesUseCase.emit(FakeDomain.ResourceMoviesList.MOVIES_LIST_1)
            val emissionLoading = awaitItem()
            Truth.assertThat(emissionLoading).isInstanceOf(BaseViewModel.UiState.Loading::class.java)

            getMoviesUseCase.emit(FakeDomain.ResourceMoviesList.MOVIES_LIST_ERROR)
            val emissionError = awaitItem()
            Truth.assertThat(emissionError).isInstanceOf(BaseViewModel.UiState.Error::class.java)

            cancelAndConsumeRemainingEvents()
        }
    }
}