package com.jgbravo.commons.exceptions

class RepositoryException(
    code: Int,
    msg: String
) : AppException(code.getAppCodeException(), msg)