package com.jgbravo.actiasystemsmobile.features.billboard.models

data class MovieFilterModel(
    var movieDeleted: MutableList<SummaryMovie> = mutableListOf()
) {
    val areActivated: Boolean get() = movieDeleted.isNotEmpty()
}