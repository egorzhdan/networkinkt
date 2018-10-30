package com.egorzh.networkinkt.platform

import com.egorzh.networkinkt.*
import java.io.IOException
import java.net.*
import kotlin.coroutines.suspendCoroutine
import kotlin.coroutines.resume

internal actual object DefaultHTTPRequestInvoker {
    actual suspend fun sendRequest(request: DefaultHTTPRequest): DefaultHTTPResponse = suspendCoroutine { continuation ->
        val con = URL(request.url).openConnection() as HttpURLConnection
        con.requestMethod = request.method.rawValue
        request.headers.forEach {
            con.setRequestProperty(it.key, it.value)
        }

        if (request.body != null) {
            con.doOutput = true
            con.outputStream.bufferedWriter().use { it.write(request.body) }
        }

        val code = con.responseCode
        val text =
                try {
                    con.inputStream.reader().readText()
                } catch (e: IOException) {
                    ""
                }

        continuation.resume(DefaultHTTPResponse(code, text))
    }
}