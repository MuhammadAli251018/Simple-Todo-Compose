package com.example.simpletodo.utl

import kotlinx.coroutines.runBlocking

abstract class TestRunner <T> : TestEvaluator<T> {
    open fun beforeEach() = Unit
    open fun afterEach() = Unit

    inline fun runTest(crossinline test: suspend () -> Unit) {
        runBlocking {
            beforeEach()
            test()
            afterEach()
        }
    }
}