package org.plaehn.adventofcode.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MathTest {

    @Test
    fun `Fibonacci numbers are computed correctly`() {
        assertThat((1..10).map { fibonacci(it) }).containsExactly(1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
    }

    @Test
    fun `Chinese remainder is computed correctly`() {
        assertThat(chineseRemainder(listOf(3, 5, 7), listOf(2, 3, 2))).isEqualTo(23)
        assertThat(chineseRemainder(listOf(3, 7, 11), listOf(1, 6, 5))).isEqualTo(181)
    }
}
