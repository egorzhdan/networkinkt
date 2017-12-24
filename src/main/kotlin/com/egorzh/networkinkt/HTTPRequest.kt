package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
expect class HTTPRequest(url: String, method: String = "GET") {
    suspend fun loadText(): String
}