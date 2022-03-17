package com.jgbravo.core.utils

import com.jgbravo.core.exceptions.CodeException

sealed class Resource<out T : Any> {

    object Loading : Resource<Nothing>()

    data class Success<out T : Any>(val data: Any?) : Resource<T>()

    data class Error(
        val code: Int = CodeException.UNKNOWN_EXCEPTION.code,
        val exception: Throwable
    ) : Resource<Nothing>()
}