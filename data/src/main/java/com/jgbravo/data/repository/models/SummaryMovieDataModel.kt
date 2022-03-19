package com.jgbravo.data.repository.models

import com.jgbravo.core.models.base.DataModel
import java.util.*

data class SummaryMovieDataModel(
    val id: Int,
    val title: String,
    val poster: String,
    val releaseDate: Date,
    val rate: Double,
    val votes: Int
) : DataModel()