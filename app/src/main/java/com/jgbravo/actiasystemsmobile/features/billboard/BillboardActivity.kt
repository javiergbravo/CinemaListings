package com.jgbravo.actiasystemsmobile.features.billboard

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import com.jgbravo.actiasystemsmobile.R
import com.jgbravo.actiasystemsmobile.databinding.ActivityBillboardBinding
import com.jgbravo.actiasystemsmobile.features.billboard.adapters.BillboardAdapter
import com.jgbravo.actiasystemsmobile.features.movieDetails.MovieDetailsActivity
import com.jgbravo.actiasystemsmobile.features.movieDetails.MovieDetailsActivity.Companion.KEY_MOVIE_ID
import com.jgbravo.presentation.BaseActivity
import com.jgbravo.presentation.extensions.*
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
        setupFabButton()
        setupBottomLayout()
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
                        showDialogError(state.title, state.message)
                        hideLoader()
                    }
                }
            }
        }

        scope.launch {
            viewModel.yearFilterState.collect {
                binding.bottomSheetFilters.root.visibilityBy { it.bottomLayoutVisible }
                binding.fabFilter.apply {
                    this.visibilityBy { it.fabVisible }
                    if (it.fabExpanded) extend() else shrink()
                }
            }
        }
    }

    private fun setupFabButton() {
        binding.fabFilter.setOnClickListener {
            if (binding.fabFilter.isExtended) {
                viewModel.cleanYearFilters()
            } else {
                viewModel.expandLayoutFilter()
            }
        }
    }

    private fun setupBottomLayout() {
        binding.bottomSheetFilters.apply {
            btnFilter.setOnClickListener {
                val validInputs = viewModel.checkInputDates(tietFromDate.text.toString(), tietToDate.text.toString())
                if (!validInputs) {
                    tietFromDate.showError(stringRes(R.string.error_enter_valid_date)!!)
                    tietToDate.showError(stringRes(R.string.error_enter_valid_date)!!)
                } else {
                    tietFromDate.cleanError()
                    tietToDate.cleanError()
                    it.hideKeyboard()
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

    private fun showDialogError(@StringRes title: Int, @StringRes message: Int) {
        showError(title, message, R.string.retry) {
            viewModel.getMovies()
        }
    }
}