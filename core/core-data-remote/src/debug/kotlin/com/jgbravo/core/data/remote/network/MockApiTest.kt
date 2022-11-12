package com.jgbravo.core.data.remote.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jgbravo.core.commons.test.rules.MainCoroutinesRule
import com.jgbravo.core.commons.test.utils.FileTestUtils
import com.jgbravo.core.Api
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.nio.charset.StandardCharsets

@ExperimentalCoroutinesApi
abstract class MockApiTest<T : Api> {

    @JvmField
    @Rule
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    protected lateinit var mockWebServer: MockWebServer

    @Before
    fun startServer() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun stopServer() {
        mockWebServer.shutdown()
    }

    fun enqueueResponse(fileRoute: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = FileTestUtils.getInputStreamFromResource(fileRoute)
        val source = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(source!!.readString(StandardCharsets.UTF_8)))
    }

    abstract fun createApi(): T
}