package com.jgbravo.core.extensions

fun <T : Any> joinLists(oldList: List<T>, newList: List<T>): List<T> {
    return when {
        oldList.isEmpty() && newList.isEmpty() -> emptyList()
        oldList.isEmpty() && newList.isNotEmpty() -> newList
        oldList.isNotEmpty() && newList.isEmpty() -> oldList
        else -> {
            (oldList + newList).distinct()
        }
    }
}