package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
object HTTP {
    fun get(url: String, headers: Map<String, String> = emptyMap()) = HTTPRequest(url, "GET", null, headers)
    fun post(url: String, headers: Map<String, String> = emptyMap(), body: String? = null) = HTTPRequest(url, "POST", body, headers)
}