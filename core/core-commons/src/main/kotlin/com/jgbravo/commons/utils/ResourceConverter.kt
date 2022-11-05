package com.jgbravo.commons.utils

import com.jgbravo.commons.exceptions.AppException
import com.jgbravo.commons.models.base.DTOModel
import com.jgbravo.commons.models.base.DataModel
import com.jgbravo.commons.models.mappers.ApiMapper
import com.jgbravo.commons.models.wrappers.ApiResponse
import com.jgbravo.commons.models.wrappers.Resource

object ResourceConverter {

    fun <DTO : DTOModel, DATA : DataModel> getDataFromApiResponse(
        apiResponse: ApiResponse<DTO>,
        mapper: ApiMapper<DTO, DATA>
    ): Resource<DATA> {
        return when (apiResponse) {
            is ApiResponse.Success -> {
                try {
                    val data = mapper.map(apiResponse.data as DTO)
                    Resource.Success(data)
                } catch (e: AppException) {
                    Resource.Error(e.code.value, e)
                } catch (e: Exception) {
                    Resource.Error(e.hashCode(), e)
                }
            }
            is ApiResponse.Error -> Resource.Error(apiResponse.code.value, apiResponse.exception)
        }
    }
}