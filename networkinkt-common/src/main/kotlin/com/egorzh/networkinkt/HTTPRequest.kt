package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
expect class HTTPRequest(url: String, method: String = "GET", headers: Map<String, String> = emptyMap()) {
    suspend fun loadText(): String
}