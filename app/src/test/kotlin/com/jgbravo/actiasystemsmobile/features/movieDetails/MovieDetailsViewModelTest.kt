package com.jgbravo.actiasystemsmobile.features.movieDetails

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.jgbravo.actiasystemsmobile.fakeDomain.fakeModels.FakeDomain
import com.jgbravo.actiasystemsmobile.fakeDomain.fakeUseCases.FakeGetMovieDetailsUseCase
import com.jgbravo.actiasystemsmobile.utils.TestDispatchers
import com.jgbravo.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest {

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var getMovieDetailsUseCase: FakeGetMovieDetailsUseCase

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        getMovieDetailsUseCase = FakeGetMovieDetailsUseCase()
        viewModel = MovieDetailsViewModel(
            dispatchers = testDispatchers,
            getMovieDetailsUseCase = getMovieDetailsUseCase
        )
    }

    @Test
    fun `movieState update to success`() = runTest {
        Truth.assertThat(viewModel.movie.value).isEqualTo(MovieDetailsViewModel.MovieState.NotStarted)

        viewModel.getMovieDetails(1)

        getMovieDetailsUseCase.emit(FakeDomain.ResourceMovieDetails.MOVIE_DETAILS)
        Truth.assertThat(viewModel.movie.value).isInstanceOf(MovieDetailsViewModel.MovieState.Success::class.java)
    }

    @Test
    fun `movieState not update cause error`() = runTest {
        Truth.assertThat(viewModel.movie.value).isEqualTo(MovieDetailsViewModel.MovieState.NotStarted)

        viewModel.getMovieDetails(1)

        getMovieDetailsUseCase.emit(FakeDomain.ResourceMovieDetails.MOVIE_DETAILS_ERROR)
        Truth.assertThat(viewModel.movie.value).isEqualTo(MovieDetailsViewModel.MovieState.NotStarted)
    }

    @Test
    fun `uiState update to loading`() = runTest {
        viewModel.uiState.test {
            viewModel.getMovieDetails(1)

            getMovieDetailsUseCase.emit(FakeDomain.ResourceMovieDetails.MOVIE_DETAILS)
            val emissionLoading = awaitItem()
            Truth.assertThat(emissionLoading).isInstanceOf(BaseViewModel.UiState.Loading::class.java)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `uiState update to error`() = runTest {
        viewModel.uiState.test {
            viewModel.getMovieDetails(1)

            getMovieDetailsUseCase.emit(FakeDomain.ResourceMovieDetails.MOVIE_DETAILS_ERROR)
            skipItems(1) // Skip loading state
            val emissionError = awaitItem()
            Truth.assertThat(emissionError).isInstanceOf(BaseViewModel.UiState.Error::class.java)

            cancelAndConsumeRemainingEvents()
        }
    }
}