package com.jgbravo.actiasystemsmobile.features.billboard

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.jgbravo.actiasystemsmobile.R
import com.jgbravo.actiasystemsmobile.features.billboard.models.MovieFilterModel
import com.jgbravo.actiasystemsmobile.features.billboard.models.SummaryMovie
import com.jgbravo.actiasystemsmobile.features.billboard.models.mappers.SummaryMovieUiMapper
import com.jgbravo.commons.extensions.joinLists
import com.jgbravo.commons.extensions.mapList
import com.jgbravo.commons.models.Resource
import com.jgbravo.domain.models.MovieDomainModel
import com.jgbravo.domain.useCases.GetMoviesUseCase
import com.jgbravo.presentation.BaseViewModel
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
    private val filters = MovieFilterModel()
    private var movieList: List<SummaryMovie> = arrayListOf()
    private val filteredMovieList: List<SummaryMovie> get() = filterList(movieList)

    private var _movies = MutableStateFlow<BillboardState>(BillboardState.NotStarted)
    val movies: StateFlow<BillboardState> get() = _movies

    private val _yearFilterState = MutableStateFlow<YearFilterState>(YearFilterState.NO_FILTERS)
    val yearFilterState: StateFlow<YearFilterState> get() = _yearFilterState

    fun getMovies() {
        viewModelScope.launch {
            getMoviesUseCase.invoke(page++).collect { resource ->
                _movies.value = when (resource) {
                    Resource.Loading -> BillboardState.Loading
                    is Resource.Success -> {
                        val newList = (resource.data as List<MovieDomainModel>).mapList(SummaryMovieUiMapper())
                        logger.d("Add ${newList.size} movies to list")
                        updateList(newList)
                        BillboardState.Success(filteredMovieList)
                    }
                    is Resource.Error -> {
                        BillboardState.Error(
                            R.string.dialog_error_no_internet_title,
                            R.string.dialog_error_no_internet_body
                        )
                    }
                }
            }
        }
    }

    private fun updateMovies() {
        _movies.value = BillboardState.Success(filteredMovieList)
    }

    private fun updateYearFilter(state: YearFilterState) {
        _yearFilterState.value = state
    }

    fun isListAvailable(): Boolean = movieList.isNotEmpty()

    private fun updateList(newMovies: List<SummaryMovie>) {
        movieList = joinLists(movieList, newMovies)
    }

    fun deleteMovie(movie: SummaryMovie) {
        filters.movieDeleted.add(movie)
        updateMovies()
    }

    fun filterByTitle(search: String?) {
        filters.searchTitle = search ?: ""
        updateMovies()
    }

    fun cleanYearFilters() {
        filters.cleanYearsFilters()
        updateMovies()
        updateYearFilter(YearFilterState.NO_FILTERS)
    }

    fun expandLayoutFilter() {
        updateYearFilter(YearFilterState.SHOW_FILTERS)
    }

    fun checkInputDates(dateFrom: String, dateTo: String): Boolean {
        filters.setYearFrom(dateFrom)
        filters.setYearTo(dateTo)
        return if (filters.hasYearFilter()) {
            updateMovies()
            updateYearFilter(YearFilterState.APPLIED_FILTERS)
            true
        } else {
            false
        }
    }


    private fun filterList(list: List<SummaryMovie>): List<SummaryMovie> {
        return list.filterByTitle().filterDeleted().filterYear()
    }

    private fun List<SummaryMovie>.filterByTitle(): List<SummaryMovie> {
        return if (filters.hasTitleFilter) {
            this.filter { it.title.lowercase().contains(filters.searchTitle.lowercase()) }
        } else {
            this
        }
    }

    private fun List<SummaryMovie>.filterDeleted(): List<SummaryMovie> {
        return if (filters.hasDeletedMovies) {
            this.filter { !filters.movieDeleted.contains(it) }
        } else {
            this
        }
    }

    private fun List<SummaryMovie>.filterYear(): List<SummaryMovie> {
        return when {
            filters.yearFrom != null && filters.yearTo != null -> {
                this.filter { it.releaseYear in filters.yearFrom!!..filters.yearTo!! }
            }
            filters.yearFrom != null -> this.filter { it.releaseYear >= filters.yearFrom!! }
            filters.yearTo != null -> this.filter { it.releaseYear <= filters.yearTo!! }
            else -> this
        }
    }

    sealed class BillboardState {
        object NotStarted : BillboardState()
        object Loading : BillboardState()
        class Success(val movies: List<SummaryMovie>) : BillboardState()
        class Error(
            @StringRes val title: Int,
            @StringRes val message: Int
        ) : BillboardState()
    }

    enum class YearFilterState(
        val bottomLayoutVisible: Boolean,
        val fabVisible: Boolean,
        val fabExpanded: Boolean
    ) {
        NO_FILTERS(false, true, false),
        SHOW_FILTERS(true, false, false),
        APPLIED_FILTERS(false, true, true)
    }
}