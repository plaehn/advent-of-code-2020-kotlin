package org.plaehn.adventofcode.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BusFinderTest {

    @Test
    fun `Find next bus to airport for small input`() {
        val busFinder = BusFinder.fromLines(readInput("small_input.txt"))

        val result = busFinder.findIdOfEarliestBusToAirportMultipliedByWaitingTime()

        assertThat(result).isEqualTo(295)
    }

    @Test
    fun `Find next bus to airport for large input`() {
        val busFinder = BusFinder.fromLines(readInput("input.txt"))

        val result = busFinder.findIdOfEarliestBusToAirportMultipliedByWaitingTime()

        assertThat(result).isEqualTo(2092)
    }

    @Test
    fun `Find earliest timestamp with subsequent departures for tiny input`() {
        val busFinder = BusFinder.fromLines(listOf("0", "17,x,13,19"))

        val earliestTime = busFinder.findEarliestTimestampWithSubsequentDepartures()

        assertThat(earliestTime).isEqualTo(3417)
    }

    @Test
    fun `Find earliest timestamp with subsequent departures for small input`() {
        val busFinder = BusFinder.fromLines(readInput("small_input.txt"))

        val earliestTime = busFinder.findEarliestTimestampWithSubsequentDepartures()

        assertThat(earliestTime).isEqualTo(1068781)
    }

    @Test
    fun `Find earliest timestamp with subsequent departures for large input`() {
        val busFinder = BusFinder.fromLines(readInput("input.txt"))

        val earliestTime = busFinder.findEarliestTimestampWithSubsequentDepartures()

        assertThat(earliestTime).isEqualTo(702970661767766L)
    }

    private fun readInput(resourceName: String): List<String> =
        BusFinder::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}