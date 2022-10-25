package com.jgbravo.actiasystemsmobile.features.movieDetails

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.jgbravo.actiasystemsmobile.R
import com.jgbravo.actiasystemsmobile.features.movieDetails.models.MovieDetails
import com.jgbravo.actiasystemsmobile.features.movieDetails.models.mappers.MovieDetailsUiMapper
import com.jgbravo.commons.models.Resource
import com.jgbravo.domain.models.MovieDetailsDomainModel
import com.jgbravo.domain.useCases.GetMovieDetailsUseCase
import com.jgbravo.presentation.BaseViewModel
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
                        MovieState.Error(R.string.dialog_error_unknown_title, R.string.dialog_error_unknown_body)
                    }
                }
            }
        }
    }

    sealed class MovieState {
        object NotStarted : MovieState()
        object Loading : MovieState()
        data class Success(val movie: MovieDetails) : MovieState()
        data class Error(
            @StringRes val title: Int,
            @StringRes val message: Int
        ) : MovieState()
    }
}