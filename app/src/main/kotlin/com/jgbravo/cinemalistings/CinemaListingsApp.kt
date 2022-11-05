package com.jgbravo.cinemalistings

import android.app.Application
import com.jgbravo.cinemalistings.utils.timber.CinemaListingsDebugTree
import com.jgbravo.cinemalistings.utils.timber.CinemaListingsReleaseTree
import com.jgbravo.commons.timber.Logger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class CinemaListingsApp : Application() {

    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(CinemaListingsDebugTree())
        } else {
            Timber.plant(CinemaListingsReleaseTree())
        }

        logger.d("CinemaListingsApp", "[onCreate] - DEBUG MODE = ${BuildConfig.DEBUG}")
    }
}