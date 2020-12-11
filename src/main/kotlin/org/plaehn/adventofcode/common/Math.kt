package org.plaehn.adventofcode.common

fun fibonacci(n: Int) =
        (2 until n)
                .fold(1 to 1) { (prev, curr), _ ->
                    curr to (prev + curr)
                }.second
