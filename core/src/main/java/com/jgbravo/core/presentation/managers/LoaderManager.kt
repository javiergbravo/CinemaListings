package com.jgbravo.core.presentation.managers

import android.content.Context
import com.jgbravo.core.presentation.customViews.SimpleLoader

open class LoaderManager {

    companion object {
        private var simpleLoader: SimpleLoader? = null

        fun showDialog(context: Context?) {
            hideDialog()
            if (context != null) {
                try {
                    simpleLoader = SimpleLoader(context).apply {
                        setCanceledOnTouchOutside(false)
                        setCancelable(false)
                    }
                    simpleLoader?.show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        fun hideDialog() {
            if (simpleLoader != null && simpleLoader?.isShowing!!) {
                simpleLoader = try {
                    simpleLoader?.dismiss()
                    null
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}
