package com.egorzh.networkinkt

import kotlin.test.*

/**
 * @author Egor Zhdan
 */
class HTTPRequestTest {
    @Test
    fun loadEmpty200okPage() {
        val req = HTTPRequest("http://httpbin.org/status/200")
        runBlocking {
            val text = req.loadText()
            assertEquals("", text)
        }
    }

    @Test
    fun loadEmpty404Page() {
        val req = HTTPRequest("http://httpbin.org/status/404")
        assertFails {
            runBlocking {
                req.loadText()
            }
        }
    }

    @Test
    fun loadSamplePage() {
        val req = HTTPRequest("http://httpbin.org/robots.txt")
        runBlocking {
            val text = req.loadText()
            assertEquals("User-agent: *\nDisallow: /deny\n", text)
        }
    }

    @Test
    fun loadHeaders() {
        val req = HTTPRequest("http://httpbin.org/headers", "GET", mapOf("MyLibraryHeader" to "networkinkt"))
        runBlocking {
            val text = req.loadText()
            assertTrue(text.contains("MyLibraryHeader", ignoreCase = true))
            assertTrue(text.contains("networkinkt", ignoreCase = true))
        }
    }
}