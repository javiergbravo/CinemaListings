package com.jgbravo.commons.extensions

import com.jgbravo.commons.exceptions.AppException
import com.jgbravo.commons.exceptions.MappingException
import com.jgbravo.commons.exceptions.MappingReason
import com.jgbravo.commons.models.base.BaseModel
import com.jgbravo.commons.models.mappers.BaseMapper

fun <IN : BaseModel, OUT : BaseModel> List<IN>?.mapList(
    mapper: BaseMapper<IN, OUT>,
    discardWrongItems: Boolean = true
): List<OUT> {
    return this?.mapNotNull { inModel ->
        try {
            mapper.map(inModel)
        } catch (e: AppException) {
            if (discardWrongItems) {
                null
            } else {
                throw MappingException(inModel::class.java.name, MappingReason.BUILD_OBJECT)
            }
        } catch (e: Exception) {
            if (discardWrongItems) {
                null
            } else {
                throw e
            }
        }
    } ?: emptyList()
}