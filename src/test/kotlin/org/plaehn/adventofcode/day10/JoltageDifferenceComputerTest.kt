package org.plaehn.adventofcode.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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

    @Test
    fun `Count distinct valid arrangements for small input`() {
        val computer = JoltageDifferenceComputer(readInput("small_input.txt"))
        val numberOfValidArrangements = computer.countDistinctValidArrangements()

        assertThat(numberOfValidArrangements).isEqualTo(8)
    }

    @Test
    fun `Count distinct valid arrangements for medium input`() {
        val computer = JoltageDifferenceComputer(readInput("medium_input.txt"))
        val numberOfValidArrangements = computer.countDistinctValidArrangements()

        assertThat(numberOfValidArrangements).isEqualTo(19208)
    }

    @Test
    fun `Count distinct valid arrangements for large input`() {
        val computer = JoltageDifferenceComputer(readInput("input.txt"))
        val numberOfValidArrangements = computer.countDistinctValidArrangements()

        assertThat(numberOfValidArrangements).isEqualTo(74049191673856)
    }

    private fun readInput(resourceName: String): List<Int> =
        JoltageDifferenceComputer::class.java
            .getResource(resourceName)
            .readText()
            .lines()
            .filter { it.isNotBlank() }
            .map { it.toInt() }
}
