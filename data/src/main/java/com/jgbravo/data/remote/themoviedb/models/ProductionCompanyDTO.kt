package com.jgbravo.data.remote.themoviedb.models


import com.jgbravo.core.models.base.DTOModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompanyDTO(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "logo_path")
    val logoPath: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "origin_country")
    val originCountry: String?
) : DTOModel()