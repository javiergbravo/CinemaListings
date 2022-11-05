package com.jgbravo.cinemalistings.utils.timber

import com.jgbravo.commons.timber.Logger
import com.jgbravo.commons.timber.LoggerImpl
import javax.inject.Inject

class CinemaListingsLoggerImpl @Inject constructor() : LoggerImpl() {

    protected override val customTag: String
        get() {
            val stackTraceElements = Throwable().stackTrace
            for (stackTraceElement in stackTraceElements) {
                if (isCinemaListingsClass(stackTraceElement)
                    && !isCinemaListingsLoggerClass(stackTraceElement)
                    && !isLoggerClass(stackTraceElement)
                ) {
                    return buildTag(stackTraceElement)
                }
            }
            return "Unknown"
        }

    private fun isCinemaListingsClass(stackTraceElement: StackTraceElement): Boolean {
        return stackTraceElement.className.startsWith(Logger.PACKAGE_APP)
    }

    private fun isCinemaListingsLoggerClass(stackTraceElement: StackTraceElement): Boolean {
        return stackTraceElement.className.contains(this::class.java.name) // "CinemaListingsLogger"
    }
}