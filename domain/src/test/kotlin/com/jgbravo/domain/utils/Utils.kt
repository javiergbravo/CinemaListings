package com.jgbravo.domain.utils

import com.jgbravo.commons.models.Resource
import com.jgbravo.commons.models.base.DomainModel

fun <T : DomainModel> MutableList<Resource<List<T>>>.getListFromFirstSuccessResult(): List<T> {
    return ((this.first() as Resource.Success<List<T>>).data as List<T>)
}

fun <T : DomainModel> MutableList<Resource<List<T>>>.getListFromLastSuccessResult(): List<T> {
    return ((this.last() as Resource.Success<List<T>>).data as List<T>)
}