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

    @Test
    fun `Middle is computed correctly`() {
        assertThat((0..127).middle()).isEqualTo(63)
        assertThat((32..63).middle()).isEqualTo(47)
    }

    @Test
    fun `Lower half is computed correctly`() {
        assertThat((0..127).lowerHalf()).isEqualTo(0..63)
        assertThat((32..63).lowerHalf()).isEqualTo(32..47)
    }

    @Test
    fun `Upper half is computed correctly`() {
        assertThat((0..63).upperHalf()).isEqualTo(32..63)
        assertThat((32..47).upperHalf()).isEqualTo(40..47)
    }
}
