package com.jgbravo.actiasystemsmobile.features.movieDetails

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import com.jgbravo.actiasystemsmobile.databinding.ActivityMovieDetailsBinding
import com.jgbravo.core.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding>() {

    override val viewModel: MovieDetailsViewModel by viewModels()

    companion object {
        const val KEY_MOVIE_ID = "movieId"
    }

    override fun getViewBinding(): ActivityMovieDetailsBinding = ActivityMovieDetailsBinding.inflate(layoutInflater)

    override fun setupView() {

    }

    override fun collectStateDataFlows() {}

    override fun collectLifecycleStateFlows(scope: CoroutineScope) {
        scope.launch {

        }
    }
}