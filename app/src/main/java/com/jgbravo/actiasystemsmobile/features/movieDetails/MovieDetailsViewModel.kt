package com.jgbravo.actiasystemsmobile.features.movieDetails

import androidx.lifecycle.viewModelScope
import com.jgbravo.actiasystemsmobile.features.movieDetails.models.MovieDetails
import com.jgbravo.actiasystemsmobile.features.movieDetails.models.mappers.MovieDetailsUiMapper
import com.jgbravo.core.models.Resource
import com.jgbravo.core.presentation.BaseViewModel
import com.jgbravo.domain.models.MovieDetailsDomainModel
import com.jgbravo.domain.useCases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    private val _movie = MutableStateFlow<MovieState>(MovieState.NotStarted)
    val movie: StateFlow<MovieState> get() = _movie

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase.invoke(movieId).collect { res ->
                _movie.value = when (res) {
                    Resource.Loading -> MovieState.Loading
                    is Resource.Success -> {
                        val movieDetails = MovieDetailsUiMapper().map((res.data as MovieDetailsDomainModel))
                        MovieState.Success(movieDetails)
                    }
                    is Resource.Error -> {
                        logger.e(res.exception)
                        MovieState.Error(res.exception.message ?: "Unknown error")
                    } // ToDo: clean
                }
            }
        }
    }

    sealed class MovieState {
        object NotStarted : MovieState()
        object Loading : MovieState()
        class Success(val movie: MovieDetails) : MovieState()
        class Error(val message: String) : MovieState()
    }
}