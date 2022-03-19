package com.jgbravo.core.data

import com.jgbravo.core.exceptions.ApiCallException
import com.jgbravo.core.exceptions.ApiCallReason
import com.jgbravo.core.exceptions.AppException
import com.jgbravo.core.models.Resource
import com.jgbravo.core.models.base.DTOModel
import com.jgbravo.core.models.base.DataModel
import com.jgbravo.core.models.mappers.ApiMapper
import retrofit2.Response

abstract class BaseRepository {

    fun <DTO : DTOModel, DATA : DataModel> getDataFromResponse(
        response: Response<DTO>,
        mapper: ApiMapper<DTO, DATA>
    ): Resource<DATA> {
        return when {
            response.isSuccessful && response.body() != null -> {
                try {
                    val data = mapper.mapToData(response.body()!!)
                    Resource.Success(data)
                } catch (e: AppException) {
                    Resource.Error(e.code.value, e)
                } catch (e: Exception) {
                    Resource.Error(response.code(), e)
                }
            }
            response.isSuccessful && response.body() == null -> {
                val e = ApiCallException(ApiCallReason.EMPTY_BODY.msg)
                Resource.Error(e.code.value, e)
            }
            else -> {
                val e = ApiCallException(response.message())
                Resource.Error(e.code.value, e)
            }
        }
    }
}