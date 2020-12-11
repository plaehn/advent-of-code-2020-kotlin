package org.plaehn.adventofcode.day11

import org.junit.Test

class GameOfSeatingTest {

    @Test
    fun `Compute joltage difference distribution product for small input`() {
        val rows = readInput("small_input.txt")

        val seatLayout = SeatLayout.fromRowStrings(rows)

        println(seatLayout)
    }

    private fun readInput(resourceName: String): List<String> =
            GameOfSeating::class.java
                    .getResource(resourceName)
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
}
