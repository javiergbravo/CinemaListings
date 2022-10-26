package com.jgbravo.actiasystemsmobile.utils.timber

import com.jgbravo.commons.timber.Logger
import com.jgbravo.commons.timber.LoggerImpl
import javax.inject.Inject

class ActiaLoggerImpl @Inject constructor() : LoggerImpl() {

    protected override val customTag: String
        get() {
            val stackTraceElements = Throwable().stackTrace
            for (stackTraceElement in stackTraceElements) {
                if (isActiaClass(stackTraceElement)
                    && !isActiaLoggerClass(stackTraceElement)
                    && !isLoggerClass(stackTraceElement)
                ) {
                    return buildTag(stackTraceElement)
                }
            }
            return "Unknown"
        }

    private fun isActiaClass(stackTraceElement: StackTraceElement): Boolean {
        return stackTraceElement.className.startsWith(Logger.PACKAGE_APP)
    }

    private fun isActiaLoggerClass(stackTraceElement: StackTraceElement): Boolean {
        return stackTraceElement.className.contains(this::class.java.name) // "ActiaLogger"
    }
}