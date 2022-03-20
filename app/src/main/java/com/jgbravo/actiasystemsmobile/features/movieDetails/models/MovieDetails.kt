package com.jgbravo.actiasystemsmobile.features.movieDetails.models

import com.jgbravo.core.models.base.UiModel

data class MovieDetails(
    val id: Int,
    val title: String,
    val director: String,
    val banner: String?,
    val releaseYear: Int,
    val duration: String,
    val description: String,
    val score: Double
) : UiModel()