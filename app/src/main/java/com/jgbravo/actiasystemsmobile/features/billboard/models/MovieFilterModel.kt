package com.jgbravo.actiasystemsmobile.features.billboard.models

data class MovieFilterModel(
    var movieDeleted: MutableList<SummaryMovie> = mutableListOf(),
    var searchTitle: String = "",
    var yearFrom: Int? = null,
    var yearTo: Int? = null
) {
    val hasDeletedMovies: Boolean get() = movieDeleted.isNotEmpty()
    val hasTitleFilter: Boolean get() = searchTitle.isNotBlank()

    fun hasValidYearFilters(dateToInput: String): Boolean {
        return when {
            dateToInput.isNotBlank() && yearTo == null -> false
            dateToInput.isBlank() && yearTo == null -> hasYearFilter()
            else -> isYearToLessThanFrom(yearTo!!)
        }
    }

    fun hasYearFilter(): Boolean = yearFrom != null || yearTo != null

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
        if (dateText.trim()
                .toIntOrNull() != null && dateText.length == 4 && isYearToLessThanFrom(dateText.toInt())
        ) {
            yearTo = dateText.toInt()
        }
    }

    fun isYearToLessThanFrom(yearTo: Int): Boolean {
        return yearFrom == null || (yearFrom != null && yearTo > yearFrom!!)
    }
}