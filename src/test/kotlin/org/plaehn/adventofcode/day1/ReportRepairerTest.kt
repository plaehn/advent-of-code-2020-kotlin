package org.plaehn.adventofcode.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class ReportRepairerTest {

    @Test
    fun `Correct code for pairs is returned`() {
        val input = readInput()
        val code = ReportRepairer().fixExpenseReport(subsetSize = 2, input)
        assertThat(code).isEqualTo(63616)
    }

    @Test
    fun `Correct code for triples is returned`() {
        val input = readInput()
        val code = ReportRepairer().fixExpenseReport(subsetSize = 3, input)
        assertThat(code).isEqualTo(67877784)
    }

    @Test
    fun `Null is returned if no code could be found`() {
        val input = setOf(1, 2, 3)
        val code = ReportRepairer().fixExpenseReport(subsetSize = 3, input)
        assertThat(code).isNull()
    }

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
    fun `Create all combinations of size 1`() {
        val numbers: Set<Int> = setOf(1, 2, 3)
        assertThat(numbers.combinations(ofSize = 1)).contains(setOf(1), setOf(2), setOf(3))
    }

    @Test
    fun `Create all combinations of size 2`() {
        val numbers: Set<Int> = setOf(1, 2, 3)
        assertThat(numbers.combinations(ofSize = 2)).containsExactlyInAnyOrder(setOf(1, 2), setOf(1, 3), setOf(2, 3))
    }

    private fun readInput(): Set<Int> = HashSet(
            ReportRepairer::class.java
                    .getResource("input.txt")
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
                    .map { Integer.valueOf(it) }
    )
}
