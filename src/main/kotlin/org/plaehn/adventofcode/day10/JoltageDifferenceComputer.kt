package org.plaehn.adventofcode.day10

import org.plaehn.adventofcode.common.fibonacci
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

    fun countDistinctValidArrangements(): Long {
        val joltageDifferences = sortedJoltages
                .mapIndexed { index, joltage -> joltage - previousJoltage(index) }

        val chunks = chunkByEqualElements(joltageDifferences.asSequence()).toList()

        val factors = chunks
                .map {
                    if (it.first() == 1) fibonacci(it.count() + 2) - 1 else 1
                }
                .map { it.toLong() }

        return factors.product()
    }

    private fun chunkByEqualElements(joltageDifferences: Sequence<Int>): Sequence<List<Int>> = sequence {
        val currentChunk = mutableListOf<Int>()
        for (joltageDifference in joltageDifferences) {
            if (currentChunk.isNotEmpty() && joltageDifference != currentChunk.first()) {
                yield(currentChunk.toList())
                currentChunk.clear()
            }
            currentChunk += joltageDifference
        }
        yield(currentChunk)
    }
}
