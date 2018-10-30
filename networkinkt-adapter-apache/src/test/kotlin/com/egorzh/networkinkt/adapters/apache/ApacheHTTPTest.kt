package com.egorzh.networkinkt.adapters.apache

import com.egorzh.networkinkt.*
import kotlin.test.*
import kotlinx.coroutines.runBlocking

/**
 * @author Egor Zhdan
 */
class ApacheHTTPTest {
    @Test
    fun loadEmpty200okPage() = runBlocking {
        val req = ApacheHTTP.get("http://httpbin.org/status/200")

        val resp = DefaultHTTPClient.sendRequest(req)
        assertEquals(200, resp.code)
        assertEquals("", resp.text)
    }

    @Test
    fun loadEmpty404Page() = runBlocking {
        val req = ApacheHTTP.get("http://httpbin.org/status/404")
        val resp = DefaultHTTPClient.sendRequest(req)
        assertEquals(404, resp.code)
    }

    @Test
    fun loadSamplePage() = runBlocking {
        val req = ApacheHTTP.get("http://httpbin.org/robots.txt")
        val resp = DefaultHTTPClient.sendRequest(req)
        assertEquals(200, resp.code)
        assertEquals("User-agent: *\nDisallow: /deny\n", resp.text)
    }
}