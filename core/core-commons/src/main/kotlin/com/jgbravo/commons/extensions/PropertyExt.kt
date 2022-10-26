package com.jgbravo.commons.extensions

import com.jgbravo.commons.exceptions.MappingException
import com.jgbravo.commons.exceptions.MappingReason
import kotlin.reflect.KProperty0

fun <T> KProperty0<T?>.getOrThrow(): T {
    return this.get() ?: throw MappingException(this.name, MappingReason.PROPERTY_NULL)
}