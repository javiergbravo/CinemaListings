package com.jgbravo.core.extensions

import android.content.res.TypedArray
import androidx.annotation.StyleableRes

fun TypedArray.getStyleableFloat(@StyleableRes styleable: Int, defaultValue: Float?): Float {
    return if (hasValue(styleable)) {
        getFloat(styleable, defaultValue ?: 1.5f)
    } else {
        defaultValue ?: 1.5f
    }
}