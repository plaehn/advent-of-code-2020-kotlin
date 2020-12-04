package org.plaehn.adventofcode.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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

    private fun readInput(): Set<Int> = HashSet(
            ReportRepairer::class.java
                    .getResource("input.txt")
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
                    .map { Integer.valueOf(it) }
    )
}
