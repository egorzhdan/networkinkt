package com.egorzh.networkinkt

import kotlin.coroutines.experimental.*
import kotlinx.coroutines.experimental.*

/**
 * @author Egor Zhdan
 */
@Suppress("EXPECTED_DECLARATION_WITH_DEFAULT_PARAMETER")
expect fun <T> runBlocking(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> T): T