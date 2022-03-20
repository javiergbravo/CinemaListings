package com.jgbravo.actiasystemsmobile.features.billboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jgbravo.actiasystemsmobile.R
import com.jgbravo.actiasystemsmobile.databinding.ItemMovieBinding
import com.jgbravo.actiasystemsmobile.features.billboard.models.SummaryMovieUiModel
import com.jgbravo.core.extensions.loadFromUrl
import com.jgbravo.core.presentation.adapters.BaseAdapter
import com.jgbravo.core.presentation.adapters.BaseItemCallback
import com.jgbravo.core.presentation.adapters.BaseViewHolder

class BillboardAdapter :
    BaseAdapter<ItemMovieBinding, BillboardAdapter.MovieCardViewHolder, SummaryMovieUiModel>() {

    override val diffCallback = object : BaseItemCallback<SummaryMovieUiModel>() {
        override fun areItemsTheSame(oldItem: SummaryMovieUiModel, newItem: SummaryMovieUiModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCardViewHolder {
        return MovieCardViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieCardViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val movie = getItem(position)
        holder.binding.apply {
            tvTitle.text = movie.title
            tvReleaseYear.text = movie.releaseYear.toString()
            imgPoster.loadFromUrl(movie.poster, R.drawable.ic_image_error)
        }
    }

    inner class MovieCardViewHolder(binding: ItemMovieBinding) : BaseViewHolder<ItemMovieBinding>(binding)
}
