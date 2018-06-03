package com.egorzh.networkinkt

@Suppress("EXPECTED_DECLARATION_WITH_DEFAULT_PARAMETER")
expect fun runBlocking(block: suspend () -> Unit)