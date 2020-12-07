package org.plaehn.adventofcode.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class BagRuleSolverTest {

    private val shinyGoldBag = Bag(modifier = "shiny", color = "gold")

    @Test
    fun `Count bags that can transitively contain shiny gold bag for small input`() {
        val rules = readInput("small_input.txt").map { BagRule.fromString(it) }

        val result = BagRuleSolverGraph(rules).canContainTransitively(shinyGoldBag)

        println(result)
        assertThat(result).containsExactlyInAnyOrder(
                Bag(modifier = "dark", color = "orange"),
                Bag(modifier = "muted", color = "yellow"),
                Bag(modifier = "light", color = "red"),
                Bag(modifier = "bright", color = "white")
        )
    }

    @Test
    fun `Count bags that can transitively contain shiny gold bag for large input`() {
        val rules = readInput("input.txt").map { BagRule.fromString(it) }

        val result = BagRuleSolverGraph(rules).canContainTransitively(shinyGoldBag)

        assertThat(result.count()).isEqualTo(257)
    }

    @Test
    fun `Count bags that are contained in shiny gold bag for small input`() {
        val rules = readInput("small_input.txt").map { BagRule.fromString(it) }

        val result = BagRuleSolverGraph(rules).countContainedBags(shinyGoldBag)

        assertThat(result).isEqualTo(32)
    }

    @Test
    fun `Count bags that are contained in shiny gold bag for another small input`() {
        val rules = readInput("another_small_input.txt").map { BagRule.fromString(it) }

        val result = BagRuleSolverGraph(rules).countContainedBags(shinyGoldBag)

        assertThat(result).isEqualTo(126)
    }

    @Test
    fun `Count bags that are contained in shiny gold bag for large input`() {
        val rules = readInput("input.txt").map { BagRule.fromString(it) }

        val result = BagRuleSolverGraph(rules).countContainedBags(shinyGoldBag)

        assertThat(result).isEqualTo(1038)
    }

    private fun readInput(resourceName: String): List<String> =
            BagRuleSolverGraph::class.java
                    .getResource(resourceName)
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
}
