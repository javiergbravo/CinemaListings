package com.jgbravo.core.extensions

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun ImageView.loadFromUrl(url: String, @DrawableRes errorDrawable: Int? = null) {
    if (errorDrawable == null) {
        Glide.with(context).load(url).transition(withCrossFade()).into(this)
    } else {
        Glide.with(context).load(url).error(errorDrawable).transition(withCrossFade()).into(this)
    }
}