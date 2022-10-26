package com.jgbravo.actiasystemsmobile.features.movieDetails

import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import com.jgbravo.actiasystemsmobile.R
import com.jgbravo.actiasystemsmobile.databinding.ActivityMovieBinding
import com.jgbravo.actiasystemsmobile.features.movieDetails.models.MovieDetails
import com.jgbravo.presentation.base.BaseActivity
import com.jgbravo.presentation.base.BaseViewModel
import com.jgbravo.presentation.extensions.getExtraInt
import com.jgbravo.presentation.extensions.loadFromUrl
import com.jgbravo.presentation.extensions.setUpExpandable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity<ActivityMovieBinding>() {

    override val viewModel: MovieDetailsViewModel by viewModels()

    companion object {
        const val KEY_MOVIE_ID = "movieId"
    }

    private val movieId: Int get() = getExtraInt(KEY_MOVIE_ID)

    override fun getViewBinding(): ActivityMovieBinding = ActivityMovieBinding.inflate(layoutInflater)

    override fun setupView() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        binding.appBarLayout.setUpExpandable(
            onCollapseToolbar = {
                binding.rbScore.visibility = View.GONE
            },
            onExpandToolbar = {
                binding.rbScore.visibility = View.VISIBLE
            }
        )

        viewModel.getMovieDetails(movieId)
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
            viewModel.movie.collect { state ->
                when (state) {
                    MovieDetailsViewModel.MovieState.NotStarted -> Unit
                    is MovieDetailsViewModel.MovieState.Success -> {
                        binding.root.visibility = View.VISIBLE
                        setupInfo(state.movie)
                        hideLoader()
                    }
                }
            }
        }
    }

    private fun setupInfo(movie: MovieDetails) {
        binding.apply {
            movie.banner?.let { imgBanner.loadFromUrl(it) }
            rbScore.rating = movie.score.toFloat()
            toolbar.title = movie.title
            movieInfo.apply {
                tvReleaseYear.text = movie.releaseYear.toString()
                tvDirector.text = movie.director
                tvDuration.text = movie.duration
                tvDescription.text = movie.description
                movie.poster?.let { imgPoster.loadFromUrl(it) }
            }
        }
    }

    private fun showDialogError(@StringRes title: Int, @StringRes message: Int) {
        showError(title, message, R.string.accept) {
            onBackPressed()
        }
    }
}