package com.jgbravo.actiasystemsmobile.features.billboard

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.jgbravo.actiasystemsmobile.fakeDomain.fakeUseCases.FakeGetMoviesUseCase
import com.jgbravo.actiasystemsmobile.utils.TestDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BillboardViewModelTest {

    private lateinit var viewModel: BillboardViewModel
    private lateinit var testDispatchers: TestDispatchers


    @Before
    fun setUp() {
        // Test Doubles
        testDispatchers = TestDispatchers()
        viewModel = BillboardViewModel(
            dispatchers = testDispatchers,
            getMoviesUseCase = FakeGetMoviesUseCase()
        )
    }

    @Test
    fun `getMovies -Success-`() = runTest {
        viewModel.getMovies()
        val job = launch() {
            viewModel.movies.test {
                testDispatchers.testDispatcher.scheduler.apply {
                    advanceTimeBy(1000L)
                    runCurrent()
                }
                val emission = awaitItem()
                println("movies sateFlow = $emission")
                println("movies list size = ${emission.size}")
                Truth.assertThat(emission.size).isEqualTo(3)
                cancelAndConsumeRemainingEvents()
            }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `getMovies pagination -Success-`() = runTest {
        viewModel.getMovies()
        viewModel.getMovies()
        val job = launch() {
            viewModel.movies.test {
                testDispatchers.testDispatcher.scheduler.apply {
                    advanceTimeBy(2000L)
                    runCurrent()
                }
                val emission = awaitItem()
                println("movies sateFlow = $emission")
                println("movies list size = ${emission.size}")
                Truth.assertThat(emission.size).isEqualTo(6)
                cancelAndConsumeRemainingEvents()
            }
        }
        job.join()
        job.cancel()
    }

    @Test
    fun `getMovies -Error-`() = runTest {
        TODO("Comment code is not working")
        /*viewModel.getMovies()
        viewModel.getMovies()
        viewModel.getMovies()
        val job = launch() {
            viewModel.uiState.test {
                testDispatchers.testDispatcher.scheduler.apply {
                    advanceTimeBy(5000L)
                    runCurrent()
                }
                val emission = awaitItem()
                println("emission = $emission")
                Truth.assertThat(emission).isInstanceOf(BaseViewModel.UiState.Error::class.java)
                cancelAndConsumeRemainingEvents()
            }
        }
        job.join()
        job.cancel()*/
    }
}