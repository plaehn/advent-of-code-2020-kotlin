package org.plaehn.adventofcode.day18

import com.github.h0tk3y.betterParse.grammar.parseToEnd
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WrongPrecedenceArithmeticsEvaluatorTest {

    private val evaluator = WrongPrecedenceArithmeticsEvaluator()

    @Test
    fun `Expressions without parentheses work`() {
        assertThat(evaluator.parseToEnd("1 + 2 * 3 + 4 * 5 + 6")).isEqualTo(231)
        assertThat(evaluator.parseToEnd("7 * 2 + 3 * 5")).isEqualTo(175)
    }

    @Test
    fun `Expressions with parentheses work`() {
        assertThat(evaluator.parseToEnd("2 * 3 + (4 * 5)")).isEqualTo(46)
        assertThat(evaluator.parseToEnd("5 + (8 * 3 + 9 + 3 * 4 * 3)")).isEqualTo(1445)
        assertThat(evaluator.parseToEnd("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))")).isEqualTo(669060)
        assertThat(evaluator.parseToEnd("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2")).isEqualTo(23340)
    }

    @Test
    fun `Sum result of all expressions from input`() {
        val sumOfAllExpressions = readInput()
            .map { evaluator.parseToEnd(it) }
            .sum()

        assertThat(sumOfAllExpressions).isEqualTo(185348874183674)
    }


    private fun readInput(): List<String> =
        WrongPrecedenceArithmeticsEvaluator::class.java
            .getResource("input.txt")
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}