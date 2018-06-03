package com.egorzh.networkinkt

import kotlin.test.*

/**
 * @author Egor Zhdan
 */
class HTTPRequestTest {
    @Test
    fun loadEmpty200okPage() = runBlocking {
        val req = HTTPRequest("http://httpbin.org/status/200")

        val text = req.getText()
        assertEquals("", text)
    }

    @Test
    fun loadEmpty404Page() = runBlocking {
        val req = HTTPRequest("http://httpbin.org/status/404")
        assertFails {
            runBlocking {
                req.getText()
            }
        }
    }

    @Test
    fun loadSamplePage() = runBlocking {
        val req = HTTPRequest("http://httpbin.org/robots.txt")
        val text = req.getText()
        assertEquals("User-agent: *\nDisallow: /deny\n", text)
    }

    @Test
    fun loadHeaders() = runBlocking {
        val req = HTTPRequest("http://httpbin.org/headers", "GET", null, mapOf("MyLibraryHeader" to "networkinkt"))
        val text = req.getText()
        assertTrue(text.contains("MyLibraryHeader", ignoreCase = true))
        assertTrue(text.contains("networkinkt", ignoreCase = true))
    }

    @Test
    fun postWithBody() = runBlocking {
        val req = HTTPRequest("http://httpbin.org/post", "POST", "a=b")
        val text = req.getText()
        println(text)
        assertTrue(text.contains("form"))
    }
}