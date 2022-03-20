package com.jgbravo.core.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.jgbravo.core.models.base.BaseModel

abstract class BaseItemCallback<M : BaseModel> : DiffUtil.ItemCallback<M>() {

    abstract override fun areItemsTheSame(oldItem: M, newItem: M): Boolean

    override fun areContentsTheSame(oldItem: M, newItem: M): Boolean = oldItem.toString() == newItem.toString()
}