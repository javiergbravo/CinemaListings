package com.jgbravo.core.commons.test.utils

object FileTestUtils {

    fun readFileFromResources(fileName: String): String {
        return getJsonContent(fileName)?.bufferedReader().use { bufferReader -> bufferReader?.readText() } ?: ""
    }

    fun getJsonContent(fileRoute: String) = javaClass.classLoader?.getResourceAsStream(fileRoute)
}
