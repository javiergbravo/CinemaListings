package com.jgbravo.remote.utils

import com.jgbravo.commons.exceptions.ApiCallException
import com.jgbravo.commons.exceptions.ApiCallReason
import com.jgbravo.commons.exceptions.getAppCodeException
import com.jgbravo.commons.extensions.isNotNull
import com.jgbravo.commons.models.base.DTOModel
import com.jgbravo.commons.models.wrappers.ApiResponse
import retrofit2.Response

object ResponseConverter {

    fun <DTO : DTOModel> convertToApiResponse(response: Response<DTO>): ApiResponse<DTO> {
        return when {
            response.isSuccessful && response.body().isNotNull() -> ApiResponse.Success(response.body()!!)
            response.isSuccessful && response.body().isNotNull() -> {
                val exception = ApiCallException(ApiCallReason.EMPTY_BODY.msg)
                ApiResponse.Error(code = exception.code, exception = exception)
            }
            else -> ApiResponse.Error(
                code = response.code().getAppCodeException(),
                exception = ApiCallException(reason = response.errorBody().toString())
            )
        }
    }
}