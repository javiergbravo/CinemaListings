package com.jgbravo.cinemalistings.features.movieDetails

import androidx.lifecycle.viewModelScope
import com.jgbravo.cinemalistings.features.movieDetails.models.MovieDetails
import com.jgbravo.cinemalistings.features.movieDetails.models.mappers.MovieDetailsUiMapper
import com.jgbravo.cinemalistings.utils.wrappers.UiError.UnknownError
import com.jgbravo.commons.models.wrappers.Resource
import com.jgbravo.commons.utils.DispatcherProvider
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
    private val dispatchers: DispatcherProvider,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel() {

    private val _movie = MutableStateFlow<MovieState>(MovieState.NotStarted)
    val movie: StateFlow<MovieState> get() = _movie

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(dispatchers.io) {
            emitLoading()
            getMovieDetailsUseCase.invoke(movieId).collect { res ->
                when (res) {
                    is Resource.Success -> {
                        val movieDetails = MovieDetailsUiMapper().map((res.data as MovieDetailsDomainModel))
                        _movie.value = MovieState.Success(movieDetails)
                    }
                    is Resource.Error -> emitErrorWithException(
                        exception = res.exception,
                        title = UnknownError.title,
                        message = UnknownError.message
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