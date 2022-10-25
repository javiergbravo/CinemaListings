package com.jgbravo.domain.models

import com.jgbravo.commons.models.base.DomainModel
import java.util.*

data class MovieDetailsDomainModel(
    val id: Int,
    val title: String,
    val director: String,
    val banner: String?,
    val poster: String?,
    val releaseDate: Date,
    val duration: Int,
    val description: String,
    val score: Double,
    val votesAmount: Int?
) : DomainModel()