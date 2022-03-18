package com.jgbravo.actiasystemsmobile

import android.app.Application
import com.jgbravo.core.timber.trees.ActiaDebugTree
import com.jgbravo.core.timber.ActiaLogger
import com.jgbravo.core.timber.trees.ActiaReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class ActiaSystemsMobileApp : Application() {

    @Inject
    lateinit var logger: ActiaLogger

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(ActiaDebugTree())
        } else {
            Timber.plant(ActiaReleaseTree())
        }
    }
}