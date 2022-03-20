package com.jgbravo.core.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.jgbravo.core.models.base.BaseModel

class BaseListCallback<MODEL : BaseModel>(
    private val oldList: List<MODEL>,
    private val newList: List<MODEL>,
    private val areContentsTheSame: (MODEL, MODEL) -> Boolean
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
}