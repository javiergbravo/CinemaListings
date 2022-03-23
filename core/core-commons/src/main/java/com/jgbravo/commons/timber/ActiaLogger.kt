package com.jgbravo.commons.timber

import java.util.regex.Pattern

interface ActiaLogger {

    companion object {
        const val PACKAGE_APP = "com.jgbravo"
        const val MAX_TAG_LENGTH = 23
        const val API_CALL = "[Api Call - OkHttp]"
        const val ACTIVITY_TAG = "[Activity lifecycle]"
        const val FRAGMENT_TAG = "[Fragment lifecycle]"
        val PATTERN_CLASS: Pattern = Pattern.compile("(\\$\\d+)+$")
    }

    fun d(message: String, vararg objects: Any)

    fun d(tag: String, message: String, vararg objects: Any)

    fun i(message: String, vararg objects: Any)

    fun i(tag: String, message: String, vararg objects: Any)

    fun w(message: String, vararg objects: Any)

    fun w(throwable: Throwable)

    fun e(message: String, vararg objects: Any)

    fun e(throwable: Throwable)

    fun time(message: String)

    fun http(message: String, vararg objects: Any)

    fun activity(name: String, lifecycle: String, vararg objects: Any)

    fun fragment(name: String, lifecycle: String, vararg objects: Any)
}