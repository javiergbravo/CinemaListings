package com.jgbravo.presentation.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jgbravo.commons.timber.Logger
import com.jgbravo.presentation.extensions.stringRes
import com.jgbravo.presentation.managers.AnalyticsLoggerImpl
import com.jgbravo.presentation.managers.LifecycleTracker
import com.jgbravo.presentation.managers.LoaderManager
import com.jgbravo.presentation.managers.ThemeManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(),
    LifecycleTracker by AnalyticsLoggerImpl() {


    @Inject
    protected lateinit var logger: Logger

    @Inject
    protected lateinit var themeManager: ThemeManager

    @Inject
    protected lateinit var loader: LoaderManager

    protected lateinit var binding: VB

    abstract val viewModel: BaseViewModel

    private var errorDialog: AlertDialog? = null

    protected abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLifecycleOwner(this) {
            logger.lifecycle(this::class.java, it.toString(), onlyDebug = false)
        }
        binding = getViewBinding()
        setContentView(binding.root)

        themeManager.updateToCurrentTheme()

        setupView()
        executeFlows()
    }

    override fun onPause() {
        hideLoader()
        super.onPause()
    }

    override fun onStop() {
        hideLoader()
        super.onStop()
    }

    override fun onDestroy() {
        hideLoader()
        super.onDestroy()
    }

    /**
     * The adapters and some variables are initialized here when the view is created.
     */
    protected abstract fun setupView()

    private fun executeFlows() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectStateFlows(this)
            }
        }
    }

    /**
     * Function to collect all flows. Use `coroutineScope.launch { ... }` for each flow/stateFlow.
     * Each flow/stateFlow will be active when activity starts and will be stopped when activity stops.
     */
    protected abstract fun collectStateFlows(scope: CoroutineScope)

    fun showLoader() {
        logger.d("Show loader...")
        loader.showDialog()
    }

    fun hideLoader() {
        logger.d("Hide loader...")
        loader.hideDialog()
    }

    protected fun showError(
        @StringRes title: Int,
        @StringRes message: Int,
        @StringRes buttonText: Int,
        whenButtonClick: ((dialog: DialogInterface) -> Unit)? = null
    ) {
        errorDialog?.dismiss()
        errorDialog = MaterialAlertDialogBuilder(this)
            .setTitle(stringRes(title))
            .setMessage(stringRes(message))
            .setNeutralButton(stringRes(buttonText)) { dialog, _ ->
                dialog.dismiss()
                whenButtonClick?.let { it(dialog) }
            }.show()
    }

    protected fun hideError() {
        errorDialog?.dismiss()
    }
}