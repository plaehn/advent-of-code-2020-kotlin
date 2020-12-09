package org.plaehn.adventofcode.day9

import org.plaehn.adventofcode.common.combinations

class XmasDecoder(val input: List<Long>, val windowSize: Int) {

    fun findFirstWronglyEncodedNumber(): Long {
        var targetIndex = windowSize

        do {
            val window = HashSet(input.subList(targetIndex - windowSize, targetIndex))
            val pairsOfDifferentNumbers = window.combinations(ofSize = 2).filter { it.first() != it.last() }
            val numberOfPairsAddingUpToTarget = pairsOfDifferentNumbers.count() { it.sum() == input[targetIndex] }
            ++targetIndex
        } while (numberOfPairsAddingUpToTarget > 0)

        return input[targetIndex - 1]
    }
}
