package org.plaehn.adventofcode.common

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class NumberExtTest {

    @Test
    fun `Multiply on empty iterable throws`() {
        val numbers: List<Int> = listOf()
        assertFailsWith<UnsupportedOperationException> { numbers.product() }
    }

    @Test
    fun `Multiply on non-empty iterable returns correct product`() {
        val numbers: List<Int> = listOf(3, 7, 2)
        assertThat(numbers.product()).isEqualTo(42)
    }
}
