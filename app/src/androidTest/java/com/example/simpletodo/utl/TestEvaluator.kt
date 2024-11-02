package com.example.simpletodo.utl

interface TestEvaluator <T> {
    abstract val rule: T.(T) -> Boolean
    infix fun  T.matches(anther: T) = rule(anther)
}