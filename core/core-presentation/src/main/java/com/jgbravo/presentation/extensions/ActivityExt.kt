package com.jgbravo.presentation.extensions

import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.jgbravo.commons.extensions.isNotNull


fun AppCompatActivity.hasExtras(): Boolean = intent?.extras.isNotNull()

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

inline fun <reified T : AppCompatActivity> Context.newIntent(body: Intent.() -> Unit): Intent =
    Intent(this, T::class.java).apply(body)

inline fun <reified T : AppCompatActivity> Context.navigateTo(body: Intent.() -> Unit) {
    startActivity(newIntent<T>(body))
}

fun AppCompatActivity.stringRes(@StringRes resource: Int): String? {
    return try {
        resources.getString(resource)
    } catch (e: Exception) {
        null
    }
}