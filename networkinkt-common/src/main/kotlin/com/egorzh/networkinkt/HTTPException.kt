package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
class HTTPException(val code: Int): RuntimeException("HTTP error $code") {
    init {
        check(code != 200, { "cannot create an exception from 200 OK" })
    }

    val isNotFound = code == 404
}