package com.jgbravo.core.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.jgbravo.core.models.base.BaseModel

abstract class BaseAdapter<VB : ViewBinding, VH : BaseViewHolder<VB>, MODEL : BaseModel> :
    RecyclerView.Adapter<VH>() {

    abstract val diffCallback: BaseItemCallback<MODEL>
    private val differ = AsyncListDiffer(this, diffCallback)
    private var onItemClickListener: ((MODEL) -> Unit)? = null

    /**
     * Example:
     * return MenuViewHolder(ItemGuideBinding.inflate(LayoutInflater.from(parent.context), parent, false))
     */
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    /**
     * Override this function always.
     * If item is not clickable override function without calling 'super'
     * If item is clickable call 'super' at start of overridden function
     */
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(getItem(position))
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    protected fun getItem(index: Int): MODEL = differ.currentList[index]

    fun submitList(list: List<MODEL>) {
        differ.submitList(list)
    }

    /*fun submitListAndNotify(list: List<M>) {
        submitList(list)
        notifyDataSetChanged()
    }*/

    fun setOnClickListener(listener: (MODEL) -> Unit) {
        onItemClickListener = listener
    }

    protected fun ViewGroup.inflater(): LayoutInflater {
        return LayoutInflater.from(this.context)
    }
}