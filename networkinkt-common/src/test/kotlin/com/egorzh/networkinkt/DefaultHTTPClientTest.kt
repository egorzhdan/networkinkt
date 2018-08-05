package com.egorzh.networkinkt

import kotlin.test.*

/**
 * @author Egor Zhdan
 */
class DefaultHTTPClientTest {
    private fun newGET(url: String) = DefaultHTTPRequest(HTTPMethod.GET, url, emptyMap(), null)

    @Test
    fun basicTextRequestGET() = runBlocking {
        HTTP.get("http://httpbin.org/status/200").getText()
    }

    @Test
    fun basicTextRequestPOST() = runBlocking {
        HTTP.post("http://httpbin.org/headers",
                headers = mapOf("MyLibraryHeader" to "networkinkt"),
                body = "param=value")
                .getText()
    }

    @Test
    fun loadEmpty200okPage() = runBlocking {
        val req = newGET("http://httpbin.org/status/200")

        val resp = DefaultHTTPClient.sendRequest(req)
        assertEquals(200, resp.code)
        assertEquals("", resp.text)
    }

    @Test
    fun loadEmpty200okPage_shorthand() = runBlocking {
        val req = newGET("http://httpbin.org/status/200")

        val text = req.getText()
        assertEquals("", text)
    }

    @Test
    fun loadEmpty404Page() = runBlocking {
        val req = newGET("http://httpbin.org/status/404")
        val resp = DefaultHTTPClient.sendRequest(req)
        assertEquals(404, resp.code)
    }

    @Test
    fun loadSamplePage() = runBlocking {
        val req = newGET("http://httpbin.org/robots.txt")
        val resp = DefaultHTTPClient.sendRequest(req)
        assertEquals(200, resp.code)
        assertEquals("User-agent: *\nDisallow: /deny\n", resp.text)
    }

    @Test
    fun loadHeaders_shorthand() = runBlocking {
        val req = DefaultHTTPRequest(HTTPMethod.GET, "http://httpbin.org/headers", mapOf("MyLibraryHeader" to "networkinkt"), null)
        val text = req.getText()
        assertTrue(text.contains("MyLibraryHeader", ignoreCase = true))
        assertTrue(text.contains("networkinkt", ignoreCase = true))
    }

    @Test
    fun postWithBody() = runBlocking {
        val req = DefaultHTTPRequest(HTTPMethod.POST, "http://httpbin.org/post", emptyMap(), "a=b")
        val resp = DefaultHTTPClient.sendRequest(req)
        assertEquals(200, resp.code)
        assertTrue(resp.text.contains("form"))
    }
}