package com.jgbravo.actiasystemsmobile.features.billboard

import androidx.activity.viewModels
import com.jgbravo.actiasystemsmobile.databinding.ActivityMainBinding
import com.jgbravo.actiasystemsmobile.features.billboard.adapters.BillboardAdapter
import com.jgbravo.core.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BillboardActivity : BaseActivity<ActivityMainBinding>() {

    override val viewModel: BillboardViewModel by viewModels()

    private lateinit var billboardAdapter: BillboardAdapter

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun setupView() {
        viewModel.getMovies()
    }

    override fun collectStateDataFlows() {}

    override fun collectLifecycleStateFlows(scope: CoroutineScope) {
        scope.launch {
            viewModel.movies.collect { state ->
                when (state) {
                    BillboardViewModel.BillboardState.NotStarted -> Unit
                    BillboardViewModel.BillboardState.Loading -> {
                        //Show Loading
                    }
                    is BillboardViewModel.BillboardState.Success -> {
                        if (!::billboardAdapter.isInitialized) {
                            setupBillboardAdapter()
                        }
                        billboardAdapter.submitList(state.movies)
                        //Hide Loading
                    }
                    is BillboardViewModel.BillboardState.Error -> {

                    }
                }
            }
        }
    }

    private fun setupBillboardAdapter() {
        billboardAdapter = BillboardAdapter()
        binding.rvBillboard.adapter = billboardAdapter
    }
}