package com.egorzh.networkinkt.adapters.apache

import com.egorzh.networkinkt.*
import org.apache.http.client.methods.*
import org.apache.http.impl.client.HttpClients

/**
 * Example of using custom [HTTPClient]
 *
 * Not intended for real-world usage yet
 *
 * @author Egor Zhdan
 */
object ApacheHTTP : HTTPClient<DefaultHTTPRequest, DefaultHTTPResponse> {
    override fun newRequest(method: HTTPMethod, url: String, headers: Map<String, String>, body: String?): DefaultHTTPRequest {
        return DefaultHTTPRequest(method, url, headers, body)
    }

    private val client = HttpClients.createDefault()

    override suspend fun sendRequest(request: DefaultHTTPRequest): DefaultHTTPResponse {
        val url = request.url
        val baseRequest = when (request.method) {
            HTTPMethod.GET -> HttpGet(url)
            HTTPMethod.POST -> HttpPost(url)
            HTTPMethod.HEAD -> HttpHead(url)
            HTTPMethod.PATCH -> HttpPatch(url)
            HTTPMethod.PUT -> HttpPut(url)
            HTTPMethod.DELETE -> HttpDelete(url)
            else -> throw UnsupportedOperationException("this request type is not supported")
        }
        val response = client.execute(baseRequest)

        val code = response.statusLine.statusCode
        val text = response.entity.content.reader().readText()
        return DefaultHTTPResponse(code, text)
    }
}