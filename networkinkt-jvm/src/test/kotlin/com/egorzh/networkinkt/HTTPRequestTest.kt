package com.egorzh.networkinkt

import kotlinx.coroutines.experimental.*
import kotlin.test.*

/**
 * @author Egor Zhdan
 */
class HTTPRequestTest {
    @Test
    fun `load empty 200ok page`() {
        val req = HTTPRequest("http://httpbin.org/status/200")
        runBlocking {
            val text = req.loadText()
            assertEquals("", text)
        }
    }

    @Test
    fun `load empty 404 page`() {
        val req = HTTPRequest("http://httpbin.org/status/404")
        assertFails {
            runBlocking {
                req.loadText()
            }
        }
    }

    @Test
    fun `load sample page`() {
        val req = HTTPRequest("http://httpbin.org/robots.txt")
        runBlocking {
            val text = req.loadText()
            assertEquals("User-agent: *\nDisallow: /deny\n", text)
        }
    }

    @Test
    fun `stream sample page`() {
        val req = HTTPRequest("http://httpbin.org/robots.txt")
        runBlocking {
            val text = req.stream().bufferedReader().readLines()
            assertEquals(text.size, 2)
            assertEquals("User-agent: *", text.firstOrNull())
            assertEquals("Disallow: /deny", text.lastOrNull())
        }
    }

    @Test
    fun `custom configure block`() {
        val req = HTTPRequest("http://httpbin.org/post", method = "POST")
        req.configure {
            doOutput = true
            outputStream.writer().use { it.write("sample12345") }
        }
        runBlocking {
            val text = req.stream().bufferedReader().readText()
            assert(text.contains("sample12345"))
        }
    }
}