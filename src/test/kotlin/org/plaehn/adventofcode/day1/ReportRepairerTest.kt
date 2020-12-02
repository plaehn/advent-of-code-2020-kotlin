package org.plaehn.adventofcode.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReportRepairerTest {

    @Test
    fun fixExpenseReportReturnsCorrectCode() {
        val input = readInput()
        val code = ReportRepairer().fixExpenseReport(input)
        assertThat(code).isEqualTo(63616)
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
