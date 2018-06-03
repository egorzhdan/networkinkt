package com.egorzh.networkinkt

import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.*

actual fun runBlocking(block: suspend () -> Unit): dynamic = promise {
    block()
}