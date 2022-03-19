package com.jgbravo.data.repository

import com.jgbravo.core.models.Resource
import com.jgbravo.data.repository.models.BillboardDataModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getBillboard(page: Int): Flow<Resource<BillboardDataModel>>
}