package com.jgbravo.data.utils

/**
 * Code taken by Elye
 * To see more details: https://github.com/elye/demo_android_mock_web_service/blob/master/app/src/test/java/com/example/mockserverexperiment/ChatTest.kt
 */
object FileTestUtils {

    fun kotlinReadFileWithNewLineFromResources(fileName: String): String {
        return getInputStreamFromResource(fileName)?.bufferedReader()
            .use { bufferReader -> bufferReader?.readText() } ?: ""
    }

    private fun getInputStreamFromResource(fileName: String) = javaClass.classLoader?.getResourceAsStream(fileName)
}
