package com.jgbravo.commons.extensions

fun Any?.isNull() = this == null

fun Any?.isNotNull() = !this.isNull()

fun Any?.ifNotNull(action: () -> Unit) {
    if (this.isNotNull()) {
        action()
    }
}