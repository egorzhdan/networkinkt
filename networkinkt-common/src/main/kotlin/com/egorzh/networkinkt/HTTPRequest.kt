package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
expect class HTTPRequest(
        url: String,
        method: String = "GET",
        body: String? = null,
        headers: Map<String, String> = emptyMap()
) {
    suspend fun send()

    suspend fun getText(): String
}