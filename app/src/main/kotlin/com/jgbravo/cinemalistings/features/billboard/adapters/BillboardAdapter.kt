package com.jgbravo.cinemalistings.features.billboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jgbravo.cinemalistings.R
import com.jgbravo.cinemalistings.databinding.ItemMovieBinding
import com.jgbravo.cinemalistings.features.billboard.models.SummaryMovie
import com.jgbravo.presentation.adapters.BaseAdapter
import com.jgbravo.presentation.adapters.BaseViewHolder
import com.jgbravo.presentation.extensions.loadFromUrl

class BillboardAdapter : BaseAdapter<ItemMovieBinding, BillboardAdapter.MovieViewHolder, SummaryMovie>() {

    private var onMovieClickListener: ((SummaryMovie) -> Unit)? = null
    private var onDeleteClickListener: ((SummaryMovie) -> Unit)? = null

    override val areContentsTheSame: (SummaryMovie, SummaryMovie) -> Boolean = { old, new -> old.id == new.id }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.binding.apply {
            tvTitle.text = movie.title
            tvReleaseYear.text = movie.releaseYear.toString()
            imgPoster.loadFromUrl(movie.poster, R.drawable.ic_image_error)

            viewDelete.setOnDeleteClickListener {
                onDeleteClickListener?.let {
                    it(movie)
                }
            }
            root.setOnClickListener {
                onMovieClickListener?.let {
                    it(movie)
                }
            }

        }
    }

    fun setOnMovieClickListener(listener: (SummaryMovie) -> Unit) {
        onMovieClickListener = listener
    }

    fun setOnDeleteClickListener(listener: (SummaryMovie) -> Unit) {
        onDeleteClickListener = listener
    }

    inner class MovieViewHolder(binding: ItemMovieBinding) :
        BaseViewHolder<ItemMovieBinding>(binding)
}
