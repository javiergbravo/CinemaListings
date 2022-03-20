package com.jgbravo.data.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Code taken by Elye
 * To see more details: https://github.com/elye/demo_android_mock_web_service/blob/master/app/src/test/java/com/example/mockserverexperiment/ChatTest.kt
 */
object FileTestUtils {

    @Throws(IOException::class)
    fun readFileWithoutNewLineFromResources(fileName: String): String {
        var inputStream: InputStream? = null
        try {
            inputStream = getInputStreamFromResource(fileName)
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))

            var str: String? = reader.readLine()
            while (str != null) {
                builder.append(str)
                str = reader.readLine()
            }
            return builder.toString()
        } finally {
            inputStream?.close()
        }
    }

    @Throws(IOException::class)
    fun readFileWithNewLineFromResources(fileName: String): String {
        var inputStream: InputStream? = null
        try {
            inputStream = getInputStreamFromResource(fileName)
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(inputStream))

            var theCharNum = reader.read()
            while (theCharNum != -1) {
                builder.append(theCharNum.toChar())
                theCharNum = reader.read()
            }

            return builder.toString()
        } finally {
            inputStream?.close()
        }
    }

    fun kotlinReadFileWithNewLineFromResources(fileName: String): String {
        return getInputStreamFromResource(fileName)?.bufferedReader()
            .use { bufferReader -> bufferReader?.readText() } ?: ""
    }

    private fun getInputStreamFromResource(fileName: String) = javaClass.classLoader?.getResourceAsStream(fileName)
}
