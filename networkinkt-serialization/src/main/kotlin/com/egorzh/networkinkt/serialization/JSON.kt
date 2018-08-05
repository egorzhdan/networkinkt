package com.egorzh.networkinkt.serialization

import com.egorzh.networkinkt.*
import kotlinx.serialization.json.JSON

/**
 * @author Egor Zhdan
 */
suspend inline fun <reified T: Any> DefaultHTTPRequest.getJSON() = JSON.parse<T>(getText())