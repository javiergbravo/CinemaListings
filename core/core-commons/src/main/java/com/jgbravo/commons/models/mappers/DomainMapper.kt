package com.jgbravo.commons.models.mappers

import com.jgbravo.commons.models.base.DataModel
import com.jgbravo.commons.models.base.DomainModel

abstract class DomainMapper<DATA : DataModel, DOMAIN : DomainModel> : BaseMapper<DATA, DOMAIN> {

    override fun map(inModel: DATA, moreInfo: HashMap<String, Any>?): DOMAIN = mapToDomain(inModel, moreInfo)

    protected abstract fun mapToDomain(dataModel: DATA, moreInfo: HashMap<String, Any>? = null): DOMAIN
}