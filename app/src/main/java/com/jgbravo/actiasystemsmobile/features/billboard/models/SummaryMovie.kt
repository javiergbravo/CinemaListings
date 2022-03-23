package com.jgbravo.actiasystemsmobile.features.billboard.models

import com.jgbravo.commons.models.base.UiModel

data class SummaryMovie(
    val id: Int,
    val title: String,
    val poster: String,
    val releaseYear: Int
) : UiModel()