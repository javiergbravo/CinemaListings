package com.jgbravo.commons.models.mappers

import com.jgbravo.commons.models.base.BaseModel

interface BaseMapper<IN : BaseModel, OUT : BaseModel> {

    fun map(inModel: IN, moreInfo: HashMap<String, Any>? = null): OUT
}