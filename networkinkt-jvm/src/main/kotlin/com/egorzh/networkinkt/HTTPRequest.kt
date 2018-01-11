package com.egorzh.networkinkt

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * @author Egor Zhdan
 */
actual class HTTPRequest actual constructor(url: String, method: String = "GET", body: String? = null, headers: Map<String, String> = emptyMap()) {
    private val con = URL(url).openConnection() as HttpURLConnection

    init {
        con.requestMethod = method
        headers.forEach {
            con.setRequestProperty(it.key, it.value)
        }

        if (body != null) {
            con.doOutput = true
            con.outputStream.bufferedWriter().use { it.write(body) }
        }
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

    actual suspend fun getText() = stream().bufferedReader().readText()

    actual suspend fun send() {
        stream()
    }
}