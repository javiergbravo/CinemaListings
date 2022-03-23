package com.jgbravo.commons.extensions

import com.jgbravo.commons.exceptions.AppException
import com.jgbravo.commons.exceptions.MappingException
import com.jgbravo.commons.exceptions.MappingReason
import com.jgbravo.commons.models.base.BaseModel
import com.jgbravo.commons.models.mappers.BaseMapper

fun <IN : BaseModel, OUT : BaseModel> List<IN>?.mapList(
    mapper: BaseMapper<IN, OUT>,
    canDiscard: Boolean = false
): List<OUT> {
    val outList = ArrayList<OUT>()
    this?.forEach { inModel ->
        try {
            val newOutModel = mapper.map(inModel)
            outList.add(newOutModel)
        } catch (e: AppException) {
            if (!canDiscard) {
                throw MappingException(inModel::class.java.name, MappingReason.BUILD_OBJECT)
            }
        } catch (e: Exception) {
            if (!canDiscard) {
                throw e
            }
        }
    }
    return outList
}