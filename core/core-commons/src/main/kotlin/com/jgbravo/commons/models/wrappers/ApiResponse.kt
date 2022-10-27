package com.jgbravo.commons.models.wrappers

import com.jgbravo.commons.exceptions.AppCodeException
import com.jgbravo.commons.models.base.DTOModel

sealed class ApiResponse<out T : DTOModel> {

    data class Success<out T : DTOModel>(val data: DTOModel) : ApiResponse<T>()

    data class Error(val code: AppCodeException, val exception: Exception) : ApiResponse<Nothing>()
}