package com.egorzh.networkinkt

/**
 * Factory of HTTP requests
 *
 * You can create your own implementations of this interface.
 * Generally you only need to implement [newRequest] and [sendRequest] methods.
 *
 * @author Egor Zhdan
 */
interface HTTPClient<Request : HTTPRequest, Response : HTTPResponse> {
    fun newRequest(method: HTTPMethod, url: String, headers: Map<String, String> = emptyMap(), body: String? = null): Request

    suspend fun sendRequest(request: Request): Response


    fun get(url: String, headers: Map<String, String> = emptyMap()): Request =
            newRequest(HTTPMethod.GET, url, headers, null)

    fun head(url: String, headers: Map<String, String> = emptyMap()): Request =
            newRequest(HTTPMethod.HEAD, url, headers, null)

    fun post(url: String, headers: Map<String, String> = emptyMap(), body: String? = null): Request =
            newRequest(HTTPMethod.POST, url, headers, body)

    fun delete(url: String, headers: Map<String, String> = emptyMap(), body: String? = null): Request =
            newRequest(HTTPMethod.DELETE, url, headers, body)

    fun patch(url: String, headers: Map<String, String> = emptyMap(), body: String? = null): Request =
            newRequest(HTTPMethod.PATCH, url, headers, body)

    fun put(url: String, headers: Map<String, String> = emptyMap(), body: String? = null): Request =
            newRequest(HTTPMethod.PUT, url, headers, body)
}