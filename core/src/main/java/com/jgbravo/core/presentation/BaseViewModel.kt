package com.jgbravo.core.presentation

import androidx.lifecycle.ViewModel
import com.jgbravo.core.timber.ActiaLogger
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var logger: ActiaLogger

    fun trackLifecycle(activityName: String, function: String) {
        logger.activity(activityName, function)
    }
}