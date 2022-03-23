package com.jgbravo.commons.models.mappers

import com.jgbravo.commons.models.base.DTOModel
import com.jgbravo.commons.models.base.DataModel

abstract class ApiMapper<DTO : DTOModel, DATA : DataModel> : BaseMapper<DTO, DATA> {

    override fun map(inModel: DTO, moreInfo: HashMap<String, Any>?): DATA = mapToData(inModel, moreInfo)

    protected abstract fun mapToData(dtoModel: DTO, moreInfo: HashMap<String, Any>? = null): DATA
}