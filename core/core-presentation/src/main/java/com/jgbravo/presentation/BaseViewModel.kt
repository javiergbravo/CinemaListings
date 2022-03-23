package com.jgbravo.presentation

import androidx.lifecycle.ViewModel
import com.jgbravo.commons.timber.ActiaLogger
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var logger: ActiaLogger

    fun trackLifecycle(activityName: String, function: String) {
        logger.activity(activityName, function)
    }
}