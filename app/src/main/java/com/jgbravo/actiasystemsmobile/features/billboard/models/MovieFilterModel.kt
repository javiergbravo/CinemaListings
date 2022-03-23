package com.jgbravo.actiasystemsmobile.features.billboard.models

data class MovieFilterModel(
    var movieDeleted: MutableList<SummaryMovie> = mutableListOf(),
    var searchTitle: String = "",
    var yearFrom: Int? = null,
    var yearTo: Int? = null
) {
    val hasDeletedMovies: Boolean get() = movieDeleted.isNotEmpty()
    val hasTitleFilter: Boolean get() = searchTitle.isNotBlank()

    fun cleanYearsFilters() {
        yearFrom = null
        yearTo = null
    }

    fun setYearFrom(dateText: String) {
        if (dateText.trim().toIntOrNull() != null && dateText.length == 4) {
            yearFrom = dateText.toInt()
        }
    }

    fun setYearTo(dateText: String) {
        if (dateText.trim().toIntOrNull() != null && dateText.length == 4 && isYearThanDateFrom(dateText.toInt())) {
            yearTo = dateText.toInt()
        }
    }

    fun hasYearFilter(): Boolean {
        return (yearFrom != null) || (yearTo != null)
    }

    private fun isYearThanDateFrom(yearTo: Int): Boolean {
        return yearFrom != null || (yearFrom != null && yearTo < yearFrom!!)
    }
}