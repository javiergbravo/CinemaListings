package com.jgbravo.core.timber

import android.util.Log
import timber.log.Timber

class ActiaReleaseTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR || priority == Log.WARN) {
            // Send error reports to Crashlytics, Sentry or other error monitoring platform
        }
    }
}