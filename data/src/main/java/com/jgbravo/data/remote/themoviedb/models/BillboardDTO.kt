package com.jgbravo.data.remote.themoviedb.models


import com.jgbravo.core.models.base.DTOModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BillboardDTO(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<MovieDTO>?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
): DTOModel()