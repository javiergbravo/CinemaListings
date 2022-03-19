package com.jgbravo.core.models.mappers

import com.jgbravo.core.models.base.DTOModel
import com.jgbravo.core.models.base.DataModel

abstract class ApiMapper<DTO : DTOModel, DATA : DataModel> : BaseMapper<DTO, DATA> {

    override fun map(inModel: DTO, moreInfo: HashMap<String, Any>?): DATA = mapToData(inModel, moreInfo)

    abstract fun mapToData(dtoModel: DTO, moreInfo: HashMap<String, Any>? = null): DATA
}