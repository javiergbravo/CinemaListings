package com.jgbravo.domain.models

import com.jgbravo.core.models.base.DomainModel
import java.util.*

data class MovieDomainModel(
    val id: Int,
    val title: String,
    val poster: String,
    val releaseDate: Date,
    val rate: Double,
    val votes: Int
) : DomainModel()