package com.jgbravo.core.extensions

import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.hasExtras(): Boolean = intent?.extras != null

fun AppCompatActivity.extrasContainsKey(key: String): Boolean {
    return hasExtras() && intent.extras?.containsKey(key) ?: false
}

fun AppCompatActivity.getExtra(key: String): Any? {
    return intent.extras?.let { extras ->
        when {
            extrasContainsKey(key) -> extras.get(key)
            else -> null
        }
    }
}

fun AppCompatActivity.getExtraInt(key: String, defaultValue: Int = 0): Int {
    return if (extrasContainsKey(key)) {
        intent.extras!!.getInt(key)
    } else {
        defaultValue
    }
}