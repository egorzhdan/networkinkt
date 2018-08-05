package com.egorzh.networkinkt

/**
 * [HTTPRequest] invocation result
 *
 * Corresponds to a single network call
 *
 * @author Egor Zhdan
 */
interface HTTPResponse {
    val code: Int
    val text: String
}