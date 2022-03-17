package com.jgbravo.core.timber

import android.os.Build
import android.os.SystemClock
import com.jgbravo.core.timber.ActiaLogger.Companion.MAX_TAG_LENGTH
import com.jgbravo.core.timber.ActiaLogger.Companion.PACKAGE_APP
import com.jgbravo.core.timber.ActiaLogger.Companion.PATTERN_CLASS
import timber.log.Timber

class ActiaLoggerImpl : ActiaLogger {

    private var lastLogTime = 0L

    private val customTag: String
        get() {
            val stackTraceElements = Throwable().stackTrace
            for (stackTraceElement in stackTraceElements) {
                if (isActiaClass(stackTraceElement) && !isActiaLoggerClass(stackTraceElement)) {
                    return buildTag(stackTraceElement)
                }
            }
            return "Unknown"
        }

    private fun isActiaClass(stackTraceElement: StackTraceElement): Boolean {
        return stackTraceElement.className.startsWith(PACKAGE_APP)
    }

    private fun isActiaLoggerClass(stackTraceElement: StackTraceElement): Boolean {
        return stackTraceElement.className.contains(this::class.java.name) // "ActiaLogger"
    }

    private fun buildTag(stackTraceElement: StackTraceElement): String {
        return String.format(
            "[Class:%s] [Function:%s] [Line:%s] ",
            createStackElementTag(stackTraceElement),
            stackTraceElement.methodName,
            stackTraceElement.lineNumber
        )
    }

    private fun createStackElementTag(element: StackTraceElement): String {
        var tag = element.className
        val m = PATTERN_CLASS.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        tag = tag.substring(tag.lastIndexOf('.') + 1)
        return if (tag.length <= MAX_TAG_LENGTH || Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tag
        } else {
            tag.substring(0, MAX_TAG_LENGTH)
        }
    }

    override fun d(message: String, vararg objects: Any) {
        Timber.tag(customTag).d(message, *objects)
    }

    override fun i(message: String, vararg objects: Any) {
        Timber.tag(customTag).i(message, *objects)
    }

    override fun i(tag: String, message: String, vararg objects: Any) {
        Timber.tag(tag).i(message, *objects)
    }

    override fun w(message: String, vararg objects: Any) {
        Timber.tag(customTag).w(message, *objects)
    }

    override fun w(throwable: Throwable) {
        Timber.tag(customTag).w(throwable)
    }

    override fun e(message: String, vararg objects: Any) {
        Timber.tag(customTag).e(message, *objects)
    }

    override fun e(throwable: Throwable) {
        Timber.tag(customTag).e(throwable)
    }

    override fun time(message: String) {
        val time = if (lastLogTime == 0L) 0 else SystemClock.elapsedRealtime() - lastLogTime
        Timber.tag(customTag).i("[LogTime: ${time / 1000.0}s] $message")
        lastLogTime = SystemClock.elapsedRealtime()
    }
}