package com.egorzh.networkinkt

/**
 * Single HTTP request
 *
 * Can be sent multiple times
 *
 * @author Egor Zhdan
 */
interface HTTPRequest {
    val method: HTTPMethod
    val url: String
    val headers: Map<String, String>
    val body: String?
}