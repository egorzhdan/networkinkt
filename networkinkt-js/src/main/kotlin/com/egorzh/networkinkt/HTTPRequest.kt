package com.egorzh.networkinkt

import org.w3c.xhr.XMLHttpRequest
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * @author Egor Zhdan
 */
actual class HTTPRequest actual constructor(private val url: String, private val method: String = "GET", private val body: String? = null, private val headers: Map<String, String> = emptyMap()) {
    private val req = XMLHttpRequest()

    fun configure(block: XMLHttpRequest.() -> Unit) = req.run(block)

    actual suspend fun getText(): String = suspendCoroutine { continuation ->
        req.open(method, url, async = true)
        headers.forEach {
            req.setRequestHeader(it.key, it.value)
        }
        req.onreadystatechange = {
            if (req.readyState == 4.toShort()) {
                if (req.status == 200.toShort()) {
                    continuation.resume(req.responseText)
                } else {
                    continuation.resumeWithException(HTTPException(req.status.toInt()))
                }
            }
        }
        req.send(body)
    }

    actual suspend fun send() {
        getText()
    }
}