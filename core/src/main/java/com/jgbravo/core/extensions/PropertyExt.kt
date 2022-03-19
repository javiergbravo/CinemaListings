package com.jgbravo.core.extensions

import com.jgbravo.core.exceptions.MappingException
import com.jgbravo.core.exceptions.MappingReason
import kotlin.reflect.KProperty0

fun <T> KProperty0<T?>.getOrThrow(): T {
    return this.get() ?: throw MappingException(this.name, MappingReason.PROPERTY_NULL)
}