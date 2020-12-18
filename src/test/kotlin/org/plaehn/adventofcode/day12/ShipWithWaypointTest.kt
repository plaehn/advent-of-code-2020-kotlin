package org.plaehn.adventofcode.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ShipWithWaypointTest {

    @Test
    fun `Navigate with instructions from small input`() {
        val ship = ShipWithWaypoint()
        readNavigationInstructions("small_input.txt")
            .forEach {
                ship.apply(it)
            }

        assertThat(ship.computeManhattanDistance()).isEqualTo(286)
    }

    @Test
    fun `Navigate with instructions from large input`() {
        val ship = ShipWithWaypoint()
        readNavigationInstructions("input.txt")
            .forEach {
                ship.apply(it)
            }

        assertThat(ship.computeManhattanDistance()).isEqualTo(138669)
    }

    private fun readNavigationInstructions(resourceName: String): List<NavigationInstruction> =
        ShipWithWaypoint::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
            .map { NavigationInstruction.fromString(it) }
}