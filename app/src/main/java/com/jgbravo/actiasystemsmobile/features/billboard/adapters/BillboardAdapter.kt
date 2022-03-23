package com.jgbravo.actiasystemsmobile.features.billboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jgbravo.actiasystemsmobile.R
import com.jgbravo.actiasystemsmobile.databinding.ItemMovieBinding
import com.jgbravo.actiasystemsmobile.features.billboard.models.SummaryMovie
import com.jgbravo.core.extensions.loadFromUrl

class BillboardAdapter :
    com.jgbravo.presentation.adapters.BaseAdapter<ItemMovieBinding, BillboardAdapter.MovieViewHolder, SummaryMovie>() {

    private var onDeleteClickListener: ((SummaryMovie) -> Unit)? = null

    override val areContentsTheSame: (SummaryMovie, SummaryMovie) -> Boolean =
        { old, new -> old.id == new.id }

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
        super.onBindViewHolder(holder, position)
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
        }
    }

    fun setOnDeleteClickListener(listener: (SummaryMovie) -> Unit) {
        onDeleteClickListener = listener
    }

    inner class MovieViewHolder(binding: ItemMovieBinding) :
        com.jgbravo.presentation.adapters.BaseViewHolder<ItemMovieBinding>(binding)
}
