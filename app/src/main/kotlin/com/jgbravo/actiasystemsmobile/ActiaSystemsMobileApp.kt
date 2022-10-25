package com.jgbravo.actiasystemsmobile

import android.app.Application
import com.jgbravo.actiasystemsmobile.utils.timber.ActiaDebugTree
import com.jgbravo.actiasystemsmobile.utils.timber.ActiaReleaseTree
import com.jgbravo.commons.timber.Logger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class ActiaSystemsMobileApp : Application() {

    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(ActiaDebugTree())
        } else {
            Timber.plant(ActiaReleaseTree())
        }

        logger.d("ActiaSystemsMobileApp", "[onCreate] - DEBUG MODE = ${BuildConfig.DEBUG}")
    }
}