package com.jgbravo.cinemalistings.features.movieDetails.models

import com.jgbravo.commons.models.base.UiModel

data class MovieDetails(
    val id: Int,
    val title: String,
    val director: String,
    val banner: String?,
    val poster: String?,
    val releaseYear: Int,
    val duration: String,
    val description: String,
    val score: Double
) : UiModel()