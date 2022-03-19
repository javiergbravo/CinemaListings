package com.jgbravo.core.exceptions

abstract class AppException(
    val code: AppCodeException,
    val msg: String
) : Exception(msg)