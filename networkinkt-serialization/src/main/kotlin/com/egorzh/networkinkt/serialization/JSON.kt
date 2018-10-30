package com.egorzh.networkinkt.serialization

import com.egorzh.networkinkt.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JSON

/**
 * @author Egor Zhdan
 */
@ImplicitReflectionSerializer
suspend inline fun <reified T: Any> DefaultHTTPRequest.getJSON(): T = JSON.parse(getText())