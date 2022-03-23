package com.jgbravo.commons.models.mappers

import com.jgbravo.commons.models.base.DomainModel
import com.jgbravo.commons.models.base.UiModel

abstract class UiMapper<DOMAIN : DomainModel, UI : UiModel> : BaseMapper<DOMAIN, UI> {

    override fun map(inModel: DOMAIN, moreInfo: HashMap<String, Any>?): UI = mapToUi(inModel, moreInfo)

    protected abstract fun mapToUi(domainModel: DOMAIN, moreInfo: HashMap<String, Any>? = null): UI
}
