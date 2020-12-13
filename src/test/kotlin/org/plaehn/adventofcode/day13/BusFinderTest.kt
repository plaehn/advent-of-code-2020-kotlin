package org.plaehn.adventofcode.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

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

    private fun readInput(resourceName: String): List<String> =
        BusFinder::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
}