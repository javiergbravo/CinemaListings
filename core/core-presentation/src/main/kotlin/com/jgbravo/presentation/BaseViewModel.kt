package com.jgbravo.presentation

import androidx.lifecycle.ViewModel
import com.jgbravo.commons.timber.Logger
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var logger: Logger

    fun trackLifecycle(activityName: String, function: String) {
        logger.activity(activityName, function)
    }

    fun debug(activityName: String, message: String) {
        logger.d(activityName, message)
    }
}