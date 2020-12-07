package org.plaehn.adventofcode.day7

import org.junit.Test

class BagRuleTest {

    @Test
    fun `Parse input`() {
        val rules = readInput("small_input.txt").map { BagRule.fromString(it) }

        println(rules.joinToString(separator = "\n"))
    }

    private fun readInput(resourceName: String): List<String> =
            Bag::class.java
                    .getResource(resourceName)
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
}
