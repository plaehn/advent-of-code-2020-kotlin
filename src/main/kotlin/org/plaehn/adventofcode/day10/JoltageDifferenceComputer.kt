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

    fun countDistinctValidArrangements(): Long {
        val joltageDifferences = sortedJoltages
                .mapIndexed { index, joltage -> joltage - previousJoltage(index) }

        val chunks = chunkByEqualElements(joltageDifferences.asSequence()).toList()

        val multipliers = chunks.map {
            if (it.first() == 1) {
                when (it.count()) {
                    1 -> 1
                    2 -> 2
                    3 -> 4
                    4 -> 7
                    else -> throw IllegalStateException()
                }
            } else 1
        }.map { it.toLong() }

        return multipliers.product()
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
