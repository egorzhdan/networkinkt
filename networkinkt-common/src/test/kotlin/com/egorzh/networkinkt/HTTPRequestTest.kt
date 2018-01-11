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
            val text = req.getText()
            assertEquals("", text)
        }
    }

    @Test
    fun loadEmpty404Page() {
        val req = HTTPRequest("http://httpbin.org/status/404")
        assertFails {
            runBlocking {
                req.getText()
            }
        }
    }

    @Test
    fun loadSamplePage() {
        val req = HTTPRequest("http://httpbin.org/robots.txt")
        runBlocking {
            val text = req.getText()
            assertEquals("User-agent: *\nDisallow: /deny\n", text)
        }
    }

    @Test
    fun loadHeaders() {
        val req = HTTPRequest("http://httpbin.org/headers", "GET", null, mapOf("MyLibraryHeader" to "networkinkt"))
        runBlocking {
            val text = req.getText()
            assertTrue(text.contains("MyLibraryHeader", ignoreCase = true))
            assertTrue(text.contains("networkinkt", ignoreCase = true))
        }
    }

    @Test
    fun postWithBody() {
        val req = HTTPRequest("http://httpbin.org/post", "POST", "a=b")
        runBlocking {
            val text = req.getText()
            assertTrue(text.contains("\"form\": {\n    \"a\": \"b\"\n  }"))
        }
    }
}