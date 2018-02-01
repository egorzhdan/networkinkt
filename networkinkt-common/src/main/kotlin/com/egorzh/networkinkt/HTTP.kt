package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
object HTTP {
    fun get(url: String, headers: Map<String, String> = emptyMap()) = HTTPRequest(url, "GET", null, headers)
    fun head(url: String, headers: Map<String, String> = emptyMap()) = HTTPRequest(url, "HEAD", null, headers)
    fun post(url: String, headers: Map<String, String> = emptyMap(), body: String? = null) = HTTPRequest(url, "POST", body, headers)
    fun delete(url: String, headers: Map<String, String> = emptyMap(), body: String? = null) = HTTPRequest(url, "DELETE", body, headers)
    fun patch(url: String, headers: Map<String, String> = emptyMap(), body: String? = null) = HTTPRequest(url, "PATCH", body, headers)
    fun put(url: String, headers: Map<String, String> = emptyMap(), body: String? = null) = HTTPRequest(url, "PUT", body, headers)
}