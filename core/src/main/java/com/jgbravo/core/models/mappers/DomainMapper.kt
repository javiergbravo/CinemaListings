package com.jgbravo.core.models.mappers

import com.jgbravo.core.models.base.DataModel
import com.jgbravo.core.models.base.DomainModel

abstract class DomainMapper<DATA : DataModel, DOMAIN : DomainModel> : BaseMapper<DATA, DOMAIN> {

    override fun map(inModel: DATA, moreInfo: HashMap<String, Any>?): DOMAIN = mapToDomain(inModel, moreInfo)

    abstract fun mapToDomain(dataModel: DATA, moreInfo: HashMap<String, Any>? = null): DOMAIN
}