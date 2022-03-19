package com.jgbravo.actiasystemsmobile.features.billboard

import androidx.lifecycle.viewModelScope
import com.jgbravo.actiasystemsmobile.features.billboard.models.SummaryMovieUiModel
import com.jgbravo.actiasystemsmobile.features.billboard.models.mappers.SummaryMovieUiMapper
import com.jgbravo.core.extensions.mapList
import com.jgbravo.core.models.Resource
import com.jgbravo.core.presentation.BaseViewModel
import com.jgbravo.domain.models.MovieDomainModel
import com.jgbravo.domain.useCases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BillboardViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel() {

    private var _movies = MutableStateFlow<BillboardState>(BillboardState.NotStarted)
    val movies: StateFlow<BillboardState> get() = _movies

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            getMoviesUseCase.invoke().collect { resource ->
                _movies.value = when (resource) {
                    Resource.Loading -> BillboardState.Loading
                    is Resource.Success -> {
                        val data = resource.data as List<MovieDomainModel>
                        val movieList = data.mapList(SummaryMovieUiMapper())
                        logger.d(movieList.toString())
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

    sealed class BillboardState {
        object NotStarted : BillboardState()
        object Loading : BillboardState()
        class Success(val movies: List<SummaryMovieUiModel>) : BillboardState()
        class Error(val message: String) : BillboardState()
    }
}