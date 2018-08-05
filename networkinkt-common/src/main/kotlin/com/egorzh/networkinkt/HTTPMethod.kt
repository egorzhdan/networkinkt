package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
data class HTTPMethod(val rawValue: String) {
    companion object {
        val GET = HTTPMethod("GET")
        val POST = HTTPMethod("POST")
        val HEAD = HTTPMethod("HEAD")
        val DELETE = HTTPMethod("DELETE")
        val PATCH = HTTPMethod("PATCH")
        val PUT = HTTPMethod("PUT")
    }
}