package com.jgbravo.actiasystemsmobile.features.billboard

import androidx.lifecycle.viewModelScope
import com.jgbravo.actiasystemsmobile.features.billboard.models.MovieFilterModel
import com.jgbravo.actiasystemsmobile.features.billboard.models.SummaryMovie
import com.jgbravo.actiasystemsmobile.features.billboard.models.mappers.SummaryMovieUiMapper
import com.jgbravo.core.extensions.joinLists
import com.jgbravo.core.extensions.mapList
import com.jgbravo.core.models.Resource
import com.jgbravo.core.presentation.BaseViewModel
import com.jgbravo.domain.models.MovieDomainModel
import com.jgbravo.domain.useCases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillboardViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel() {

    private var page = 1
    private var movieList: List<SummaryMovie> = arrayListOf()
    private val filters = MovieFilterModel()

    private var _movies = MutableStateFlow<BillboardState>(BillboardState.NotStarted)
    val movies: StateFlow<BillboardState> get() = _movies

    fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase.invoke(page++).collect { resource ->
                _movies.value = when (resource) {
                    Resource.Loading -> BillboardState.Loading
                    is Resource.Success -> {
                        val newList =
                            (resource.data as List<MovieDomainModel>).mapList(SummaryMovieUiMapper())
                        logger.d(movieList.toString())
                        updateList(newList)
                        BillboardState.Success(movieList)
                    }
                    is Resource.Error -> {
                        logger.e(resource.exception)
                        BillboardState.Error(resource.exception.message ?: "Unknown error")
                    } // ToDo: clean
                }
            }
        }
    }

    fun isListAvailable(): Boolean = movieList.isNotEmpty()

    private fun updateList(newMovies: List<SummaryMovie>) {
        movieList = joinLists(movieList, newMovies)
    }

    fun deleteMovie(movie: SummaryMovie) {
        filters.movieDeleted.add(movie)
        movieList = movieList.filter { it.id != movie.id }
        _movies.value = BillboardState.Success(movieList)
    }

    sealed class BillboardState {
        object NotStarted : BillboardState()
        object Loading : BillboardState()
        class Success(val movies: List<SummaryMovie>) : BillboardState()
        class Error(val message: String) : BillboardState()
    }
}