package com.egorzh.networkinkt

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * @author Egor Zhdan
 */
actual class HTTPRequest actual constructor(val url: String, val method: String = "GET") {
    private val con = URL(url).openConnection() as HttpURLConnection

    init {
        con.requestMethod = method
    }

    fun configure(block: HttpURLConnection.() -> Unit) = con.run(block)

    suspend fun stream(): InputStream = suspendCoroutine { continuation ->
        val code = con.responseCode

        if (code in 200..299) {
            continuation.resume(con.inputStream)
        } else {
            continuation.resumeWithException(HTTPException(code))
        }
    }

    actual suspend fun loadText() = stream().bufferedReader().readText()
}