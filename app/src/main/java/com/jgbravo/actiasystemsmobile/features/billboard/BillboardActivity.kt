package com.jgbravo.actiasystemsmobile.features.billboard

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jgbravo.actiasystemsmobile.R
import com.jgbravo.actiasystemsmobile.databinding.ActivityBillboardBinding
import com.jgbravo.actiasystemsmobile.features.billboard.adapters.BillboardAdapter
import com.jgbravo.actiasystemsmobile.features.movieDetails.MovieDetailsActivity
import com.jgbravo.actiasystemsmobile.features.movieDetails.MovieDetailsActivity.Companion.KEY_MOVIE_ID
import com.jgbravo.presentation.BaseActivity
import com.jgbravo.presentation.extensions.navigateTo
import com.jgbravo.presentation.extensions.onReachBottom
import com.jgbravo.presentation.extensions.stringRes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BillboardActivity : BaseActivity<ActivityBillboardBinding>(),
    SearchView.OnQueryTextListener {

    override val viewModel: BillboardViewModel by viewModels()

    private lateinit var billboardAdapter: BillboardAdapter

    override fun getViewBinding(): ActivityBillboardBinding = ActivityBillboardBinding.inflate(layoutInflater)

    override fun setupView() {
        if (!viewModel.isListAvailable()) {
            viewModel.getMovies()
        }
    }

    override fun collectStateFlows(scope: CoroutineScope) {
        scope.launch {
            viewModel.movies.collect { state ->
                when (state) {
                    BillboardViewModel.BillboardState.NotStarted -> Unit
                    BillboardViewModel.BillboardState.Loading -> {
                        showLoader()
                    }
                    is BillboardViewModel.BillboardState.Success -> {
                        if (!::billboardAdapter.isInitialized) {
                            setupBillboardAdapter()
                        }
                        billboardAdapter.submitList(state.movies)
                        hideLoader()
                    }
                    is BillboardViewModel.BillboardState.Error -> {
                        showError(state.title, state.message)
                        hideLoader()
                    }
                }
            }
        }
    }

    private fun setupBillboardAdapter() {
        billboardAdapter = BillboardAdapter()
        binding.rvBillboard.apply {
            adapter = billboardAdapter
            onReachBottom { viewModel.getMovies() }
        }
        billboardAdapter.setOnClickListener { movie ->
            navigateTo<MovieDetailsActivity> { putExtra(KEY_MOVIE_ID, movie.id) }
        }
        billboardAdapter.setOnDeleteClickListener {
            viewModel.deleteMovie(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_billboard, menu)
        val search = menu.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_theme -> {
                showLoader()
                themeManager.toggleTheme()
            }
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(word: String?): Boolean {
        viewModel.filterByTitle(word)
        return true
    }

    private fun showError(@StringRes title: Int, @StringRes message: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle(stringRes(title))
            .setMessage(stringRes(message))
            .setNeutralButton(stringRes(R.string.retry)) { dialog, _ ->
                dialog.dismiss()
                viewModel.getMovies()
            }.show()
    }
}