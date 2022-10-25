package com.jgbravo.actiasystemsmobile.utils.timber

import com.jgbravo.commons.timber.ReleaseTree

class ActiaReleaseTree : ReleaseTree() {

    override fun sendError(priority: Int, tag: String?, message: String, t: Throwable?) = Unit

    override fun sendWarning(priority: Int, tag: String?, message: String, t: Throwable?) = Unit
}