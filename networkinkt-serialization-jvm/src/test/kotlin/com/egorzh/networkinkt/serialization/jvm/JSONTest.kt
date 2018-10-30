package com.egorzh.networkinkt.serialization.jvm

import com.egorzh.networkinkt.*
import com.egorzh.networkinkt.serialization.getJSON
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.*
import kotlin.test.*

/**
 * @author Egor Zhdan
 */
@ImplicitReflectionSerializer
class JSONTest {
    @Test
    fun getJSON() {
        runBlocking {
            val agent = HTTP.get("http://httpbin.org/user-agent").getJSON<UserAgent>()
            assertTrue(agent.agent.isNotBlank())
        }
    }

    @Serializable
    data class UserAgent(@SerialName("user-agent") val agent: String)
}