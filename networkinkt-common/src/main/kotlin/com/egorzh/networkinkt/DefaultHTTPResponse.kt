package com.egorzh.networkinkt

/**
 * @author Egor Zhdan
 */
data class DefaultHTTPResponse(override val code: Int,
                               override val text: String) : HTTPResponse