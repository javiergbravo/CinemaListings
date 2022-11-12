package com.jgbravo.core.data.remote.network

sealed class MockStatus(open val statusCode: Int) {

    data class Success(override val statusCode: Int = 200) : MockStatus(statusCode)

    data class Error(override val statusCode: Int) : MockStatus(statusCode)
}