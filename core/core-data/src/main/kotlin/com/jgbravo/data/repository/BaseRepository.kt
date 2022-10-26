package com.jgbravo.data.repository

import com.jgbravo.commons.exceptions.ApiCallException
import com.jgbravo.commons.exceptions.ApiCallReason
import com.jgbravo.commons.exceptions.AppException
import com.jgbravo.commons.extensions.isNotNull
import com.jgbravo.commons.extensions.isNull
import com.jgbravo.commons.models.Resource
import com.jgbravo.commons.models.base.DTOModel
import com.jgbravo.commons.models.base.DataModel
import com.jgbravo.commons.models.mappers.ApiMapper
import retrofit2.Response

abstract class BaseRepository {

    fun <DTO : DTOModel, DATA : DataModel> getDataFromResponse(
        response: Response<DTO>,
        mapper: ApiMapper<DTO, DATA>
    ): Resource<DATA> {
        return when {
            response.isSuccessful && response.body().isNotNull() -> {
                try {
                    val data = mapper.map(response.body()!!)
                    Resource.Success(data)
                } catch (e: AppException) {
                    Resource.Error(e.code.value, e)
                } catch (e: Exception) {
                    Resource.Error(response.code(), e)
                }
            }
            response.isSuccessful && response.body().isNull() -> {
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