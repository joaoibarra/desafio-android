package com.joaoibarra.gitapp.features.repository

import android.support.test.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


internal class MockServerDispatcher {

    /**
     * Return ok response from mock server
     */

    companion object {
        val requestDispatcher = object : Dispatcher() {

            override fun dispatch(request: RecordedRequest): MockResponse {

                if (request.path == "search/repositories") {
                    return MockResponse().setResponseCode(200).setBody(InstrumentationRegistry.getContext().assets.open("mock_repositories.json").toString())
                } else if (request.path == "repos/") {
                    return MockResponse().setResponseCode(200).setBody("{codes:FakeCode}")
                }
                return MockResponse().setResponseCode(404)
            }
        }
    }

    /**
     * Return error response from mock server
     */
    internal inner class ErrorDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {

            return MockResponse().setResponseCode(400)

        }
    }
}