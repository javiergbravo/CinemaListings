package com.jgbravo.core.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.jgbravo.core.presentation.managers.ThemeManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private val TAG = this::class.java.simpleName

    @Inject
    protected lateinit var themeManager: ThemeManager

    protected lateinit var binding: VB

    abstract val viewModel: BaseViewModel

    protected abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trackLifecycle("onCreate")
        binding = getViewBinding()
        setContentView(binding.root)

        themeManager.updateToCurrentTheme()

        setupView()
        executeFlows()
    }

    override fun onStart() {
        trackLifecycle("onStart")
        super.onStart()
    }

    override fun onResume() {
        trackLifecycle("onResume")
        super.onResume()
    }

    override fun onPause() {
        trackLifecycle("onPause")
        super.onPause()
    }

    override fun onStop() {
        trackLifecycle("onStop")
        super.onStop()
    }

    override fun onDestroy() {
        trackLifecycle("onDestroy")
        super.onDestroy()
    }

    /**
     * The adapters and some variables are initialized here when the view is created.
     */
    protected abstract fun setupView()

    private fun executeFlows() {
        collectStateDataFlows()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectLifecycleStateFlows(this)
            }
        }
    }

    /**
     * Collect stateFlows until it has data, then cancel coroutine. Each stateFlow must be called like this:
     * viewModel.stateflowName.collectStateFlowOnce(lifecycleScope, listOf( 'condition' )) { ... }
     * where 'condition' is the data that you are waiting for to cancel the collect.
     */
    protected abstract fun collectStateDataFlows()

    /**
     * Function to collect all flows. Use `coroutineScope.launch { ... }` for each flow/stateFlow.
     * Each flow/stateFlow will be active when activity starts and will be stopped when activity stops.
     */
    protected abstract fun collectLifecycleStateFlows(scope: CoroutineScope)

    private fun trackLifecycle(function: String) {
        viewModel.trackLifecycle(TAG, function)
    }
}