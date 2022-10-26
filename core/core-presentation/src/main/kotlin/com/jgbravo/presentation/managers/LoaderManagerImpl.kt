package com.jgbravo.presentation.managers

import android.content.Context
import com.jgbravo.commons.extensions.isNotNull
import com.jgbravo.presentation.customViews.SimpleLoader
import javax.inject.Inject

class LoaderManagerImpl @Inject constructor(
    private val activityContext: Context
) : LoaderManager {

    private var simpleLoader: SimpleLoader? = null

    override fun showDialog() {
        hideDialog()
        try {
            simpleLoader = SimpleLoader(activityContext).apply {
                setCanceledOnTouchOutside(false)
                setCancelable(false)
            }
            simpleLoader?.show()
        } catch (_: Exception) {
        }
    }

    override fun hideDialog() {
        if (simpleLoader.isNotNull() && simpleLoader?.isShowing!!) {
            simpleLoader = try {
                simpleLoader?.dismiss()
                null
            } catch (e: Exception) {
                null
            }
        }
    }
}
