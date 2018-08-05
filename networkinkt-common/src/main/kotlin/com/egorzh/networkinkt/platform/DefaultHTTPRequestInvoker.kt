package com.egorzh.networkinkt.platform

import com.egorzh.networkinkt.*

/**
 * Performs platform-related operations
 *
 * Implementation detail of the default client
 *
 * @author Egor Zhdan
 */
internal expect object DefaultHTTPRequestInvoker {
    suspend fun sendRequest(request: DefaultHTTPRequest): DefaultHTTPResponse
}