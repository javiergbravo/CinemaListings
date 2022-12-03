package com.jgbravo.commons.extensions

/**
 * Check if value of variable is null
 * @receiver Any? Variable nullable
 * @return Boolean True if is null False if is not null
 */
fun Any?.isNull() = this == null

/**
 * Check if calue of variable is NOT null
 * @receiver Any? Variable nullable
 * @return Boolean True if is NOT null False if is null
 */
fun Any?.isNotNull() = !this.isNull()

/**
 * Do an action when value of variable is NOT null
 * @receiver Any? Variable nullable
 * @param action Function0<Unit> Callback to do if variable is NOT null
 */
fun Any?.ifNotNull(action: (Any) -> Unit) {
    if (this.isNotNull()) {
        action(this!!)
    }
}