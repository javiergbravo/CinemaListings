package com.jgbravo.presentation.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.drawable(@DrawableRes drawableRes: Int): Drawable? {
    return try {
        ContextCompat.getDrawable(this, drawableRes)
    } catch (e: Exception) {
        null
    }
}