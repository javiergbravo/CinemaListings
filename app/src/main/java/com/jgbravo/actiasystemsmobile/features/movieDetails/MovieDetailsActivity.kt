package com.jgbravo.actiasystemsmobile.features.movieDetails

import android.view.View
import androidx.activity.viewModels
import com.jgbravo.actiasystemsmobile.databinding.ActivityMovieBinding
import com.jgbravo.actiasystemsmobile.features.movieDetails.models.MovieDetails
import com.jgbravo.core.extensions.getExtraInt
import com.jgbravo.core.extensions.loadFromUrl
import com.jgbravo.core.extensions.setUpExpandable
import com.jgbravo.core.presentation.BaseActivity
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

    override fun collectStateDataFlows() {}

    override fun collectLifecycleStateFlows(scope: CoroutineScope) {
        scope.launch {
            viewModel.movie.collect { state ->
                when (state) {
                    MovieDetailsViewModel.MovieState.NotStarted -> Unit
                    MovieDetailsViewModel.MovieState.Loading -> {
                        // showLoading()
                    }
                    is MovieDetailsViewModel.MovieState.Success -> {
                        setupInfo(state.movie as MovieDetails)
                        // hideLoading(
                    }
                    is MovieDetailsViewModel.MovieState.Error -> {
                        // hideLoading()
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
            }
        }
    }
}