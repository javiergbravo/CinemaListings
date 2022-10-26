package com.jgbravo.commons.timber

import android.os.SystemClock
import com.jgbravo.commons.timber.Logger.Companion.API_CALL
import com.jgbravo.commons.timber.Logger.Companion.MAX_TAG_LENGTH
import com.jgbravo.commons.timber.Logger.Companion.PATTERN_CLASS
import timber.log.Timber

abstract class LoggerImpl : Logger {

    private var lastLogTime = 0L

    protected abstract val customTag: String

    protected fun buildTag(stackTraceElement: StackTraceElement): String {
        return String.format(
            "[C:%s] [F:%s] [L:%s] ",
            createStackElementTag(stackTraceElement),
            stackTraceElement.methodName,
            stackTraceElement.lineNumber
        )
    }

    protected fun isLoggerClass(stackTraceElement: StackTraceElement): Boolean {
        return stackTraceElement.className.contains(this::class.java.name)
    }

    private fun createStackElementTag(element: StackTraceElement): String {
        var tag = element.className
        val m = PATTERN_CLASS.matcher(tag)
        if (m.find()) {
            tag = m.replaceAll("")
        }
        tag = tag.substring(tag.lastIndexOf('.') + 1)
        return if (tag.length <= MAX_TAG_LENGTH) {
            tag
        } else {
            tag.substring(0, MAX_TAG_LENGTH)
        }
    }

    override fun d(message: String, vararg objects: Any) {
        Timber.tag(customTag).d(message, *objects)
    }

    override fun d(tag: String, message: String, vararg objects: Any) {
        Timber.tag(tag).d(message, *objects)
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

    override fun http(message: String, vararg objects: Any) {
        Timber.tag(API_CALL).d(message, *objects)
    }

    override fun <T> lifecycle(clazz: Class<T>, lifecycleEvent: String, onlyDebug: Boolean) {
        val tag = "lifecycle"
        val message = "${clazz.simpleName} -> $lifecycleEvent"
        if (onlyDebug) {
            d(tag, message)
        } else {
            i(tag, message)
        }
    }
}