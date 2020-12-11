package org.plaehn.adventofcode.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MathTest {

    @Test
    fun `Fibonacci numbers are computed correctly`() {
        assertThat((1..10).map { fibonacci(it) }).containsExactly(1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
    }
}
