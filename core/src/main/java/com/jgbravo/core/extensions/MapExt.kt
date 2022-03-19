package com.jgbravo.core.extensions

import com.jgbravo.core.exceptions.AppException
import com.jgbravo.core.exceptions.MappingException
import com.jgbravo.core.exceptions.MappingReason
import com.jgbravo.core.models.base.BaseModel
import com.jgbravo.core.models.mappers.BaseMapper

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