package com.jgbravo.remote.themoviedb.models


import com.jgbravo.commons.models.base.DTOModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountryDTO(
    @Json(name = "iso_3166_1")
    val iso31661: String?,
    @Json(name = "name")
    val name: String?
) : DTOModel()