package com.jgbravo.commons.models

import com.jgbravo.commons.exceptions.AppCodeException

sealed class Resource<out T : Any> {

    data class Success<out T : Any>(val data: Any?) : Resource<T>()

    data class Error(
        val code: Int = AppCodeException.UNKNOWN_EXCEPTION.value,
        val exception: Exception
    ) : Resource<Nothing>()
}