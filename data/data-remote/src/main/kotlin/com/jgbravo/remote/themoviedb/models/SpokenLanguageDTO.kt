package com.jgbravo.remote.themoviedb.models


import com.jgbravo.commons.models.base.DTOModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguageDTO(
    @Json(name = "english_name")
    val englishName: String?,
    @Json(name = "iso_639_1")
    val iso6391: String?,
    @Json(name = "name")
    val name: String?
) : DTOModel()