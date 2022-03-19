package com.jgbravo.actiasystemsmobile.features.billboard.models

import com.jgbravo.core.models.base.UiModel

data class SummaryMovieUiModel(
    val id: Int,
    val title: String,
    val poster: String,
    val releaseYear: Int
) : UiModel()