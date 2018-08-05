package com.egorzh.networkinkt

import com.egorzh.networkinkt.platform.DefaultHTTPRequestInvoker

/**
 * @author Egor Zhdan
 */
object DefaultHTTPClient : HTTPClient<DefaultHTTPRequest, DefaultHTTPResponse> {
    override fun newRequest(method: HTTPMethod, url: String, headers: Map<String, String>, body: String?) =
            DefaultHTTPRequest(method, url, headers, body)

    override suspend fun sendRequest(request: DefaultHTTPRequest): DefaultHTTPResponse =
            DefaultHTTPRequestInvoker.sendRequest(request)
}