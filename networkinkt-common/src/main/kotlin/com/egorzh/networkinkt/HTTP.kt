package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
object HTTP {
    fun get(url: String, headers: Map<String, String> = emptyMap()) = HTTPRequest(url, "GET", headers)
    fun post(url: String, headers: Map<String, String> = emptyMap()) = HTTPRequest(url, "POST", headers)
}