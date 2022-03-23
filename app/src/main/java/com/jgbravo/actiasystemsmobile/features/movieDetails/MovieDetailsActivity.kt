package com.jgbravo.actiasystemsmobile.features.movieDetails

import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jgbravo.actiasystemsmobile.R
import com.jgbravo.actiasystemsmobile.databinding.ActivityMovieBinding
import com.jgbravo.actiasystemsmobile.features.movieDetails.models.MovieDetails
import com.jgbravo.presentation.BaseActivity
import com.jgbravo.presentation.extensions.getExtraInt
import com.jgbravo.presentation.extensions.loadFromUrl
import com.jgbravo.presentation.extensions.setUpExpandable
import com.jgbravo.presentation.extensions.stringRes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
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
            viewModel.movie.collect { state ->
                when (state) {
                    MovieDetailsViewModel.MovieState.NotStarted -> Unit
                    MovieDetailsViewModel.MovieState.Loading -> showLoader()
                    is MovieDetailsViewModel.MovieState.Success -> {
                        binding.root.visibility = View.VISIBLE
                        setupInfo(state.movie)
                        hideLoader()
                    }
                    is MovieDetailsViewModel.MovieState.Error -> {
                        hideLoader()
                        showError(state.title, state.message)
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

    private fun showError(@StringRes title: Int, @StringRes message: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle(stringRes(title))
            .setMessage(stringRes(message))
            .setNeutralButton(stringRes(R.string.accept)) { dialog, _ ->
                dialog.dismiss()
                onBackPressed()
            }.show()
    }
}