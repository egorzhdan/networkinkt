package com.egorzh.networkinkt.platform

import com.egorzh.networkinkt.*
import org.w3c.xhr.XMLHttpRequest
import kotlin.coroutines.experimental.suspendCoroutine

internal actual object DefaultHTTPRequestInvoker {
    actual suspend fun sendRequest(request: DefaultHTTPRequest): DefaultHTTPResponse = suspendCoroutine { continuation ->
        val req = XMLHttpRequest()
        req.open(request.method.rawValue, request.url, async = true)
        request.headers.forEach {
            req.setRequestHeader(it.key, it.value)
        }

        req.onload = {
            val response = DefaultHTTPResponse(req.status.toInt(), req.responseText)
            continuation.resume(response)
        }
        req.send(request.body)
    }
}