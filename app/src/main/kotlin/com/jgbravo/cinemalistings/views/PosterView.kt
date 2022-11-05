package com.jgbravo.cinemalistings.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import com.jgbravo.cinemalistings.R
import com.jgbravo.presentation.extensions.getStyleableFloat

class PosterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    companion object {
        const val DEFAULT_RATIO = 1f
    }

    private var ratio: Float = DEFAULT_RATIO

    init {
        attrs?.let {
            context.withStyledAttributes(it, R.styleable.PosterView) {
                ratio = getStyleableFloat(R.styleable.PosterView_ratio, 1.5f)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = measuredWidth
        var height = measuredHeight

        if (width == 0 && height == 0) {
            return
        }

        if (width > 0) {
            height = (width * ratio).toInt()
        } else {
            width = (height / ratio).toInt()
        }

        setMeasuredDimension(width, height)
    }
}