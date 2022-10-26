package com.jgbravo.presentation.managers

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

interface LifecycleTracker {

    fun registerLifecycleOwner(owner: LifecycleOwner, printLifecycle: (event: Lifecycle.Event) -> Unit)
}

class AnalyticsLoggerImpl : LifecycleTracker, LifecycleEventObserver {

    private var printLifecycle: ((event: Lifecycle.Event) -> Unit)? = null

    override fun registerLifecycleOwner(owner: LifecycleOwner, printLifecycle: (event: Lifecycle.Event) -> Unit) {
        owner.lifecycle.addObserver(this)
        this.printLifecycle = printLifecycle
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE,
            Lifecycle.Event.ON_START,
            Lifecycle.Event.ON_RESUME,
            Lifecycle.Event.ON_PAUSE,
            Lifecycle.Event.ON_DESTROY -> printLifecycle?.let { it(event) }
            else -> Unit
        }
    }
}