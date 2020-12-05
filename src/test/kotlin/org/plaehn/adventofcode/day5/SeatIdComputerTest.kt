package org.plaehn.adventofcode.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SeatIdComputerTest {

    private val seatIdComputer = SeatIdComputer()

    @Test
    fun `Compute seat id for examples`() {
        assertThat(seatIdComputer.computeSeatId("BFFFBBFRRR")).isEqualTo(567)
        assertThat(seatIdComputer.computeSeatId("FFFBBBFRRR")).isEqualTo(119)
        assertThat(seatIdComputer.computeSeatId("BBFFBBFRLL")).isEqualTo(820)
    }

    @Test
    fun `Find highest seat id in input`() {
        val maxSeatId = readInput().map { seatIdComputer.computeSeatId(it) }.maxOrNull()

        assertThat(maxSeatId).isEqualTo(826)
    }

    private fun readInput(): List<String> =
            SeatIdComputer::class.java
                    .getResource("input.txt")
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
}
