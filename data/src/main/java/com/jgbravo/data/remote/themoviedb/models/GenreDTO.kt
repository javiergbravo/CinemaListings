package com.jgbravo.data.remote.themoviedb.models


import com.jgbravo.core.models.base.DTOModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreDTO(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
) : DTOModel()