package com.jgbravo.data.repository.models

import com.jgbravo.commons.models.base.DataModel
import java.util.*

data class MovieDetailsDataModel(
    val id: Int,
    val title: String,
    val director: String,
    val banner: String,
    val poster: String?,
    val releaseDate: Date,
    val duration: Int,
    val description: String,
    val score: Double,
    val votesAmount: Int?
) : DataModel()