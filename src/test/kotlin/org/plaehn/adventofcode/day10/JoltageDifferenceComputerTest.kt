package org.plaehn.adventofcode.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class JoltageDifferenceComputerTest {

    @Test
    fun `Compute joltage difference distribution product for small input`() {
        val computer = JoltageDifferenceComputer(readInput("small_input.txt"))
        val product = computer.computeJoltageDifferenceDistributionProduct()

        assertThat(product).isEqualTo(35)
    }

    @Test
    fun `Compute joltage difference distribution product for medium input`() {
        val computer = JoltageDifferenceComputer(readInput("medium_input.txt"))
        val product = computer.computeJoltageDifferenceDistributionProduct()

        assertThat(product).isEqualTo(220)
    }

    @Test
    fun `Compute joltage difference distribution product for large input`() {
        val computer = JoltageDifferenceComputer(readInput("input.txt"))
        val product = computer.computeJoltageDifferenceDistributionProduct()

        assertThat(product).isEqualTo(2738)
    }

    private fun readInput(resourceName: String): List<Int> =
            JoltageDifferenceComputer::class.java
                    .getResource(resourceName)
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }
}
