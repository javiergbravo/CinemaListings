package com.jgbravo.presentation

import androidx.lifecycle.ViewModel
import com.jgbravo.commons.timber.AppLogger
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var logger: AppLogger

    fun trackLifecycle(activityName: String, function: String) {
        logger.activity(activityName, function)
    }

    fun debug(activityName: String, message: String) {
        logger.d(activityName, message)
    }
}