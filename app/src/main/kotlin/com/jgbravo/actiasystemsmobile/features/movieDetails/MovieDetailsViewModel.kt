package com.jgbravo.actiasystemsmobile.features.movieDetails

import androidx.lifecycle.viewModelScope
import com.jgbravo.actiasystemsmobile.R
import com.jgbravo.actiasystemsmobile.features.movieDetails.models.MovieDetails
import com.jgbravo.actiasystemsmobile.features.movieDetails.models.mappers.MovieDetailsUiMapper
import com.jgbravo.commons.models.Resource
import com.jgbravo.domain.models.MovieDetailsDomainModel
import com.jgbravo.domain.useCases.GetMovieDetailsUseCase
import com.jgbravo.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    companion object {
        private const val UNKNOWN_ERROR_TITLE = R.string.dialog_error_unknown_title
        private const val UNKNOWN_ERROR_MESSAGE = R.string.dialog_error_unknown_body
    }

    private val _movie = MutableStateFlow<MovieState>(MovieState.NotStarted)
    val movie: StateFlow<MovieState> get() = _movie

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            emitLoading()
            getMovieDetailsUseCase.invoke(movieId).collect { res ->
                when (res) {
                    is Resource.Success -> {
                        val movieDetails = MovieDetailsUiMapper().map((res.data as MovieDetailsDomainModel))
                        _movie.value = MovieState.Success(movieDetails)
                    }
                    is Resource.Error -> emitErrorWithException(
                        exception = res.exception,
                        title = UNKNOWN_ERROR_TITLE,
                        message = UNKNOWN_ERROR_MESSAGE
                    )
                }
            }
        }
    }

    sealed class MovieState {
        object NotStarted : MovieState()
        data class Success(val movie: MovieDetails) : MovieState()
    }
}