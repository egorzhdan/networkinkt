package com.egorzh.networkinkt

actual fun runBlocking(block: suspend () -> Unit) = kotlinx.coroutines.runBlocking { block() }