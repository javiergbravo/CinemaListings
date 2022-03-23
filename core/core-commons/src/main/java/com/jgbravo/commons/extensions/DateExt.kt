package com.jgbravo.core.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(pattern: String): Date? {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return try {
        dateFormat.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.toString(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return try {
        dateFormat.format(this) ?: ""
    } catch (e: Exception) {
        ""
    }
}

private fun Date.calendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

fun Date.day(): Int = calendar().get(Calendar.DAY_OF_MONTH)

fun Date.month(): Int = calendar().get(Calendar.MONTH)

fun Date.year(): Int = calendar().get(Calendar.YEAR)
