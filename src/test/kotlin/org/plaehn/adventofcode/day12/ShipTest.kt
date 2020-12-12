package org.plaehn.adventofcode.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ShipTest {

    @Test
    fun `Navigate with instructions from small input`() {
        val ship = Ship()
        readNavigationInstructions("small_input.txt")
            .forEach {
                ship.apply(it)
            }

        assertThat(ship.computeManhattanDistance()).isEqualTo(25)
    }

    @Test
    fun `Navigate with instructions from large input`() {
        val ship = Ship()
        readNavigationInstructions("input.txt")
            .forEach {
                ship.apply(it)
            }

        assertThat(ship.computeManhattanDistance()).isEqualTo(2270)
    }

    private fun readNavigationInstructions(resourceName: String): List<NavigationInstruction> =
        Ship::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
            .map { NavigationInstruction.fromString(it) }
}