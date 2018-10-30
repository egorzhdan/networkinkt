package com.egorzh.networkinkt

import kotlinx.coroutines.*
import kotlin.coroutines.*

actual fun runBlocking(block: suspend () -> Unit): dynamic = TODO()//promise { block() }