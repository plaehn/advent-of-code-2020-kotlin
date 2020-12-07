package org.plaehn.adventofcode.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class BagRuleSolverTest {

    @Test
    fun `Solve bag problem for small input`() {
        val rules = readInput("small_input.txt").map { BagRule.fromString(it) }

        val result = BagRuleSolver(rules).canContainTransitively(Bag(modifier = "shiny", color = "gold"))

        assertThat(result.count()).isEqualTo(4)
    }

    @Test
    fun `Solve bag problem for large input`() {
        val rules = readInput("input.txt").map { BagRule.fromString(it) }

        val result = BagRuleSolver(rules).canContainTransitively(Bag(modifier = "shiny", color = "gold"))

        assertThat(result.count()).isEqualTo(257)
    }

    private fun readInput(resourceName: String): List<String> =
            Bag::class.java
                    .getResource(resourceName)
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
}
