package com.jgbravo.actiasystemsmobile.features.billboard

import androidx.activity.viewModels
import com.jgbravo.actiasystemsmobile.databinding.ActivityMainBinding
import com.jgbravo.core.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BillboardActivity : BaseActivity<ActivityMainBinding>() {

    override val viewModel: BillboardViewModel by viewModels()

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
                        val qqq = state.movies
                        //logger.d("Kiwi", "$qqq")
                        //Hide Loading
                    }
                    is BillboardViewModel.BillboardState.Error -> {

                    }
                }
            }
        }
    }
}