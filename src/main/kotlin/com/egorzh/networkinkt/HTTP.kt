package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
object HTTP {
    fun get(url: String) = HTTPRequest(url, "GET")
    fun post(url: String) = HTTPRequest(url, "POST")
}