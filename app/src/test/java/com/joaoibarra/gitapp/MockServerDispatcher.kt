package com.joaoibarra.gitapp

import android.support.test.InstrumentationRegistry
import android.util.Log
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest


internal class MockServerDispatcher {

    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {

        override fun dispatch(request: RecordedRequest): MockResponse {
            Log.i("LOL",  request.path)
            if (request.path == "search/repositories") {
                return MockResponse().setResponseCode(200).setBody(InstrumentationRegistry.getContext().getAssets().open("mock_repositories.json").toString())
            } else if (request.path == "repos/") {
                return MockResponse().setResponseCode(200).setBody("{codes:FakeCode}")
            }
            return MockResponse().setResponseCode(404)
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