package com.jgbravo.core.models.mappers

import com.jgbravo.core.models.base.BaseModel

interface BaseMapper<IN : BaseModel, OUT : BaseModel> {

    fun map(inModel: IN, moreInfo: HashMap<String, Any>? = null): OUT
}