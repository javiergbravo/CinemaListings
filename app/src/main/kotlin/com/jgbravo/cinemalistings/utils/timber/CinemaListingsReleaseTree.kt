package com.jgbravo.cinemalistings.utils.timber

import com.jgbravo.commons.timber.ReleaseTree

class CinemaListingsReleaseTree : ReleaseTree() {

    override fun sendError(priority: Int, tag: String?, message: String, t: Throwable?) = Unit

    override fun sendWarning(priority: Int, tag: String?, message: String, t: Throwable?) = Unit
}