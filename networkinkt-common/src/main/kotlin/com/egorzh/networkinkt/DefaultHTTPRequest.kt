package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
data class DefaultHTTPRequest(override val method: HTTPMethod,
                              override val url: String,
                              override val headers: Map<String, String>,
                              override val body: String?) : HTTPRequest {

    suspend fun send(): DefaultHTTPResponse = DefaultHTTPClient.sendRequest(this)

    suspend fun getText(): String = send().text
}