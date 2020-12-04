package org.plaehn.adventofcode.common

fun Iterable<Int>.product(): Int = this.reduce(Int::times)

fun Iterable<Long>.product(): Long = this.reduce(Long::times)
