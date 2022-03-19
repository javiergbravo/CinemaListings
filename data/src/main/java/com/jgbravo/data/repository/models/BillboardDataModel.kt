package com.jgbravo.data.repository.models

import com.jgbravo.core.models.base.DataModel

data class BillboardDataModel(
    val movieList: List<MovieDataModel>,
    val page: Int?,
    val totalPages: Int?
) : DataModel()