package org.plaehn.adventofcode.common

fun fibonacci(n: Int) =
    (2 until n)
        .fold(1 to 1) { (prev, curr), _ ->
            curr to (prev + curr)
        }.second

// From https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
fun chineseRemainder(numbers: List<Long>, remainders: List<Long>): Long {
    val prod = numbers.product()
    var sum = 0L
    for (i in numbers.indices) {
        val p = prod / numbers[i]
        sum += remainders[i] * modularMultiplicativeInverse(p, numbers[i]) * p
    }
    return sum % prod
}

// From https://rosettacode.org/wiki/Chinese_remainder_theorem#Kotlin
fun modularMultiplicativeInverse(a: Long, b: Long): Long {
    if (b == 1L) return 1
    var aa = a
    var bb = b
    var x0 = 0L
    var x1 = 1L
    while (aa > 1) {
        val q = aa / bb
        var t = bb
        bb = aa % bb
        aa = t
        t = x0
        x0 = x1 - q * x0
        x1 = t
    }
    if (x1 < 0) x1 += b
    return x1
}
