package com.jgbravo.core.exceptions

enum class CodeException(val code: Int) {
    UNKNOWN_EXCEPTION(0),
    MAPPING_EXCEPTION(-1),
    API_CALL_EXCEPTION(-10)
}