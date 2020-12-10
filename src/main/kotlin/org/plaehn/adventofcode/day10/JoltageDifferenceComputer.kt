package org.plaehn.adventofcode.day10

import org.plaehn.adventofcode.common.product

class JoltageDifferenceComputer(adapterJoltages: List<Int>) {

    private val sortedJoltages = (adapterJoltages + listOf(3 + (adapterJoltages.maxOrNull() ?: 0))).sorted()

    fun computeJoltageDifferenceDistributionProduct(): Int =
            sortedJoltages
                    .mapIndexed { index, joltage -> joltage - previousJoltage(index) }
                    .groupingBy { it }
                    .eachCount()
                    .map { it.value }
                    .product()

    private fun previousJoltage(index: Int): Int = if (index == 0) 0 else sortedJoltages[index - 1]
}
