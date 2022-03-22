package com.jgbravo.core.models

import com.jgbravo.core.exceptions.AppCodeException

sealed class Resource<out T : Any> {

    object Loading : Resource<Nothing>()

    data class Success<out T : Any>(val data: Any?) : Resource<T>()

    data class Error(
        val code: Int = AppCodeException.UNKNOWN_EXCEPTION.value,
        val exception: Exception
    ) : Resource<Nothing>()
}