package com.jgbravo.remote.themoviedb.network.mock.dispatchers

import com.jgbravo.commons.extensions.isNull
import com.jgbravo.core.commons.test.utils.FileTestUtils
import com.jgbravo.core.data.remote.network.MockStatus
import com.jgbravo.remote.themoviedb.network.mock.MockResource
import com.jgbravo.remote.utils.TheMovieDbUtils.Endpoints
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

class MockServerDispatcher {

    internal class RequestDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            val mockResponse = MockResponse()

            val fileRoute = when (request.path!!.split("?")[0].replaceFirst("/", "")) {
                Endpoints.DISCOVER_MOVIE -> "${MockResource.SUCCESS}/DiscoverMovie.json"
                // ToDo: fix endpoint with parameter inside path
                // Endpoints.MOVIE_DETAILS -> "${MockResource.SUCCESS}/MovieDetails.json"
                else -> null
            }
            return if (fileRoute.isNull()) {
                MockResponse().setResponseCode(400)
            } else {
                val inputStream = FileTestUtils.getJsonContent(fileRoute!!)
                val source = inputStream?.source()?.buffer()
                return mockResponse
                    .setResponseCode(MockStatus.Success().statusCode)
                    .setBody(source!!.readString(StandardCharsets.UTF_8))
            }
        }
    }

    // ToDo: Use it and modify to get more responses
    internal class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
        }
    }
}