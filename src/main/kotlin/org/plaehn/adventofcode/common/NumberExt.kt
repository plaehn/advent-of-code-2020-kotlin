package org.plaehn.adventofcode.common

fun Iterable<Int>.product(): Int = this.reduce(Int::times)

fun Iterable<Long>.product(): Long = this.reduce(Long::times)

fun IntRange.lowerHalf(): IntRange = this.first..this.middle()

fun IntRange.upperHalf(): IntRange = this.middle() + 1..this.last

fun IntRange.middle(): Int = this.first + (this.last - this.first) / 2

