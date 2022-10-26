package com.jgbravo.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.jgbravo.commons.models.base.BaseModel
import kotlin.properties.Delegates

abstract class BaseAdapter<VB : ViewBinding, VH : BaseViewHolder<VB>, MODEL : BaseModel> : RecyclerView.Adapter<VH>() {

    abstract val areContentsTheSame: (MODEL, MODEL) -> Boolean

    private var asyncList: List<MODEL> by Delegates.observable(emptyList()) { _, oldList, newList ->
        val diffCallback = BaseListCallback(oldList, newList, areContentsTheSame)
        DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
    }

    /**
     * Example:
     * return MovieViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
     */
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun getItemCount(): Int = asyncList.size

    protected fun getItem(index: Int): MODEL = asyncList[index]

    fun submitList(list: List<MODEL>) {
        asyncList = list
    }
}