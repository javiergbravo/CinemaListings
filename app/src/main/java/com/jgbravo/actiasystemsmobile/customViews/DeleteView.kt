package com.jgbravo.actiasystemsmobile.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.jgbravo.actiasystemsmobile.databinding.ItemImgDeleteBinding

class DeleteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ItemImgDeleteBinding.inflate(LayoutInflater.from(context), this, true)

    private var onDeleteClickListener: (() -> Unit)? = null

    init {
        binding.root.setOnClickListener {
            onDeleteClickListener?.let {
                it()
            }
        }
    }

    fun setOnDeleteClickListener(listener: () -> Unit) {
        onDeleteClickListener = listener
    }
}