package com.jgbravo.commons.extensions

import com.jgbravo.commons.exceptions.MappingException
import com.jgbravo.commons.exceptions.MappingReason
import kotlin.reflect.KProperty0

/**
 * Get a property of a class or throw MappingException
 *  Example to use: model::property.getOrThrow()
 * @receiver KProperty0<T?> Property to get
 * @return T Value of property
 */
fun <T> KProperty0<T?>.getOrThrow(): T {
    return this.get() ?: throw MappingException(this.name, MappingReason.PROPERTY_NULL)
}