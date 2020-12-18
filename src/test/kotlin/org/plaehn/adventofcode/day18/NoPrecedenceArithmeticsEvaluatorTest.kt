package org.plaehn.adventofcode.day18

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NoPrecedenceArithmeticsEvaluatorTest {

    private val evaluator = NoPrecedenceArithmeticsEvaluator()

    @Test
    fun `Expressions without parentheses work`() {
        assertThat(evaluator.parseToEnd("1 + 2 * 3 + 4 * 5 + 6")).isEqualTo(71)
        assertThat(evaluator.parseToEnd("7 * 2 + 3 * 5")).isEqualTo(85)
    }

    @Test
    fun `Expressions with parentheses work`() {
        assertThat(evaluator.parseToEnd("2 * 3 + (4 * 5)")).isEqualTo(26)
        assertThat(evaluator.parseToEnd("5 + (8 * 3 + 9 + 3 * 4 * 3)")).isEqualTo(437)
        assertThat(evaluator.parseToEnd("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")).isEqualTo(12240)
        assertThat(evaluator.parseToEnd("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")).isEqualTo(13632)
    }

    @Test
    fun `Sum result of all expressions from input`() {
        val sumOfAllExpressions = readInput()
            .map { evaluator.parseToEnd(it) }
            .sum()

        assertThat(sumOfAllExpressions).isEqualTo(11297104473091)
    }


    private fun readInput(): List<String> =
        NoPrecedenceArithmeticsEvaluator::class.java
            .getResource("input.txt")
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}