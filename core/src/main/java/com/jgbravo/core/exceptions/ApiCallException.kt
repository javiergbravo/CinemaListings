package com.jgbravo.core.exceptions

data class ApiCallException(
    private val reason: ApiCallReason = ApiCallReason.UNKNOWN
) : Exception(reason.msg)

enum class ApiCallReason(val msg: String) {
    UNKNOWN("Unknown error from api response"),
    NULL_OBJECT("Null object from api response"),
    EMPTY_BODY("Empty body from api response")
}
