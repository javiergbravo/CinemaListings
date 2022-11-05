package com.jgbravo.commons.timber

import android.util.Log
import timber.log.Timber

abstract class ReleaseTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.ERROR -> sendError(priority, tag, message, t)
            Log.WARN -> sendWarning(priority, tag, message, t)
            else -> Unit
        }
    }

    /** Send ERROR reports to Crashlytics, Sentry or another error monitoring platform **/
    abstract fun sendError(priority: Int, tag: String?, message: String, t: Throwable?)

    /** Send WARNING reports to Crashlytics, Sentry or another error monitoring platform **/
    abstract fun sendWarning(priority: Int, tag: String?, message: String, t: Throwable?)
}