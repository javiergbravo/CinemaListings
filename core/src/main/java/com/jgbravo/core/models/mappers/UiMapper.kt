package com.jgbravo.core.models.mappers

import com.jgbravo.core.models.base.DomainModel
import com.jgbravo.core.models.base.UiModel

abstract class UiMapper<DOMAIN : DomainModel, UI : UiModel> : BaseMapper<DOMAIN, UI> {

    override fun map(inModel: DOMAIN, moreInfo: HashMap<String, Any>?): UI = mapToUi(inModel, moreInfo)

    protected abstract fun mapToUi(domainModel: DOMAIN, moreInfo: HashMap<String, Any>? = null): UI
}
