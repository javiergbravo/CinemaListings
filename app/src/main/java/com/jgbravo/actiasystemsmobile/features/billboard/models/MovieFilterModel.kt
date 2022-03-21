package com.jgbravo.actiasystemsmobile.features.billboard.models

data class MovieFilterModel(
    var movieDeleted: MutableList<SummaryMovie> = mutableListOf(),
    var searchTitle: String = ""
) {
    val hasDeletedMovies: Boolean get() = movieDeleted.isNotEmpty()
    val hasTitleFilter: Boolean get() = searchTitle.isNotBlank()

    fun cleanFilters() {
        movieDeleted = mutableListOf()
        searchTitle = ""
    }
}