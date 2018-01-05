package com.egorzh.networkinkt

import kotlinx.coroutines.experimental.CoroutineScope
import kotlin.coroutines.experimental.*

/**
 * @author Egor Zhdan
 */
actual fun <T> runBlocking(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> T): T {
    return kotlinx.coroutines.experimental.runBlocking(context, block)
}