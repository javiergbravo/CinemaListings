package com.jgbravo.cinemalistings.features.billboard

import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import com.jgbravo.cinemalistings.R
import com.jgbravo.cinemalistings.databinding.ActivityBillboardBinding
import com.jgbravo.cinemalistings.features.billboard.adapters.BillboardAdapter
import com.jgbravo.cinemalistings.features.movieDetails.MovieDetailsActivity
import com.jgbravo.cinemalistings.features.movieDetails.MovieDetailsActivity.Companion.KEY_MOVIE_ID
import com.jgbravo.presentation.base.BaseActivity
import com.jgbravo.presentation.base.BaseViewModel
import com.jgbravo.presentation.extensions.cleanError
import com.jgbravo.presentation.extensions.hideKeyboard
import com.jgbravo.presentation.extensions.navigateTo
import com.jgbravo.presentation.extensions.onReachBottom
import com.jgbravo.presentation.extensions.showError
import com.jgbravo.presentation.extensions.stringRes
import com.jgbravo.presentation.extensions.visibilityBy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BillboardActivity : BaseActivity<ActivityBillboardBinding>(), SearchView.OnQueryTextListener {

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
            viewModel.uiState.collect {
                when (it) {
                    BaseViewModel.UiState.Loading -> {
                        showLoader()
                        hideError()
                    }
                    is BaseViewModel.UiState.Error -> {
                        hideLoader()
                        showDialogError(it.title, it.message)
                    }
                }
            }
        }
        scope.launch {
            viewModel.movies.collect {
                setupBillboardAdapter()
                billboardAdapter.submitList(it)
                hideLoader()
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
                binding.bottomSheetFilters.tietFromDate.text?.clear()
                binding.bottomSheetFilters.tietToDate.text?.clear()
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
        if (::billboardAdapter.isInitialized) {
            return
        }
        billboardAdapter = BillboardAdapter()
        binding.rvBillboard.apply {
            adapter = billboardAdapter
            onReachBottom { viewModel.getMovies() }
        }
        billboardAdapter.setOnMovieClickListener { movie ->
            navigateTo<MovieDetailsActivity> { putExtra(KEY_MOVIE_ID, movie.id) }
        }
        billboardAdapter.setOnDeleteClickListener { movie ->
            viewModel.deleteMovie(movie)
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