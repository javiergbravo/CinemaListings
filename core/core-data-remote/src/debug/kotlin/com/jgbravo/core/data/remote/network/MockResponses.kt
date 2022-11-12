package com.jgbravo.core.data.remote.network

sealed interface MockStatus {

    object Success : MockStatus

    data class Error(val statusCode: Int) : MockStatus
}