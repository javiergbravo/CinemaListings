package com.jgbravo.commons.timber.trees

import android.util.Log
import timber.log.Timber

class ActiaReleaseTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.ERROR -> {
                // Send error reports to Crashlytics, Sentry or other error monitoring platform
            }
            Log.WARN -> {
                // Send warning reports to Crashlytics, Sentry or other error monitoring platform
            }
            else -> Unit
        }
    }
}