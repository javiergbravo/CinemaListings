package com.jgbravo.core.exceptions

class RepositoryException(
    code: Int,
    msg: String
) : AppException(code.getAppCodeException(), msg)