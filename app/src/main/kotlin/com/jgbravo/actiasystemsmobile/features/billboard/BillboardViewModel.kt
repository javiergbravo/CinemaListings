package com.jgbravo.actiasystemsmobile.features.billboard

import androidx.lifecycle.viewModelScope
import com.jgbravo.actiasystemsmobile.R
import com.jgbravo.actiasystemsmobile.features.billboard.models.MovieFilterModel
import com.jgbravo.actiasystemsmobile.features.billboard.models.SummaryMovie
import com.jgbravo.actiasystemsmobile.features.billboard.models.mappers.SummaryMovieUiMapper
import com.jgbravo.commons.extensions.isNotNull
import com.jgbravo.commons.extensions.joinLists
import com.jgbravo.commons.extensions.mapList
import com.jgbravo.commons.models.Resource
import com.jgbravo.commons.utils.DispatcherProvider
import com.jgbravo.domain.models.MovieDomainModel
import com.jgbravo.domain.useCases.GetMoviesUseCase
import com.jgbravo.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillboardViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel() {

    companion object {
        const val NO_INTERNET_TITLE = R.string.dialog_error_no_internet_title
        const val NO_INTERNET_MESSAGE = R.string.dialog_error_no_internet_body
    }

    private var page = 1
    private val filters = MovieFilterModel()
    private var movieList: List<SummaryMovie> = arrayListOf()
    private val filteredMovieList: List<SummaryMovie> get() = filterList(movieList)

    private var _movies = MutableStateFlow<List<SummaryMovie>>(emptyList())
    val movies: StateFlow<List<SummaryMovie>> get() = _movies

    private val _yearFilterState = MutableStateFlow<YearFilterState>(YearFilterState.NO_FILTERS)
    val yearFilterState: StateFlow<YearFilterState> get() = _yearFilterState

    fun getMovies() {
        viewModelScope.launch(dispatchers.io) {
            emitLoading()
            getMoviesUseCase.invoke(page++).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val newList = (resource.data as List<MovieDomainModel>).mapList(SummaryMovieUiMapper())
                        //logger.d("Add ${newList.size} movies to list")
                        updateList(newList)
                        _movies.emit(filteredMovieList)
                    }
                    is Resource.Error -> emitError(title = NO_INTERNET_TITLE, message = NO_INTERNET_MESSAGE)
                }
            }
        }
    }

    private fun updateMovies() {
        viewModelScope.launch(dispatchers.io) {
            _movies.emit(filteredMovieList)
        }
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
        return if (filters.hasValidYearFilters(dateTo)) {
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
            filters.yearFrom.isNotNull() && filters.yearTo.isNotNull() -> {
                this.filter { it.releaseYear in filters.yearFrom!!..filters.yearTo!! }
            }
            filters.yearFrom.isNotNull() -> this.filter { it.releaseYear >= filters.yearFrom!! }
            filters.yearTo.isNotNull() -> this.filter { it.releaseYear <= filters.yearTo!! }
            else -> this
        }
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