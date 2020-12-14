package org.plaehn.adventofcode.common

import kotlin.math.withSign

fun Iterable<Int>.product(): Int = this.reduce(Int::times)

fun Iterable<Long>.product(): Long = this.reduce(Long::times)

fun IntRange.lowerHalf(): IntRange = this.first..this.middle()

fun IntRange.upperHalf(): IntRange = this.middle() + 1..this.last

fun IntRange.middle(): Int = this.first + (this.last - this.first) / 2

fun Int.withSign(sign: Int): Int = this.toDouble().withSign(sign).toInt()

