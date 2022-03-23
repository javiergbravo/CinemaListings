package com.jgbravo.commons.exceptions

abstract class AppException(
    val code: AppCodeException,
    val msg: String
) : Exception(msg)