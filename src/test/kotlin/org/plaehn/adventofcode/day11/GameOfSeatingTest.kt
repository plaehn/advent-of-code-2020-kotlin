package org.plaehn.adventofcode.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameOfSeatingTest {

    @Test
    fun `Compute number of occupied seats in stable seat layout for small input`() {
        val rows = readInput("small_input.txt")
        val gameOfSeating = GameOfSeating(initialSeatLayout = SeatLayout.fromRowStrings(rows))

        val numberOfOccupiedSeats = gameOfSeating.computeStableSeatLayout().countOccupiedSeats()

        assertThat(numberOfOccupiedSeats).isEqualTo(37)
    }

    @Test
    fun `Compute number of occupied seats in stable seat layout for large input`() {
        val rows = readInput("input.txt")
        val gameOfSeating = GameOfSeating(initialSeatLayout = SeatLayout.fromRowStrings(rows))

        val numberOfOccupiedSeats = gameOfSeating.computeStableSeatLayout().countOccupiedSeats()

        assertThat(numberOfOccupiedSeats).isEqualTo(2281)
    }

    @Test
    fun `Compute number of occupied seats in stable (part 2) seat layout for small input`() {
        val rows = readInput("small_input.txt")
        val gameOfSeating = GameOfSeating(
            initialSeatLayout = SeatLayout.fromRowStrings(rows),
            numberOfOccupiedSeatsToLeaveSeatThreshold = 5,
            considerOnlyDirectlyAdjacentSeats = false
        )

        val numberOfOccupiedSeats = gameOfSeating.computeStableSeatLayout().countOccupiedSeats()

        assertThat(numberOfOccupiedSeats).isEqualTo(26)
    }

    @Test
    fun `Compute number of occupied seats in stable (part 2) seat layout for large input`() {
        val rows = readInput("input.txt")
        val gameOfSeating = GameOfSeating(
            initialSeatLayout = SeatLayout.fromRowStrings(rows),
            numberOfOccupiedSeatsToLeaveSeatThreshold = 5,
            considerOnlyDirectlyAdjacentSeats = false
        )

        val numberOfOccupiedSeats = gameOfSeating.computeStableSeatLayout().countOccupiedSeats()

        assertThat(numberOfOccupiedSeats).isEqualTo(2085)
    }

    private fun readInput(resourceName: String): List<String> =
        GameOfSeating::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}
