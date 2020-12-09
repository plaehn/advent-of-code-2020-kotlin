package org.plaehn.adventofcode.day9

import org.plaehn.adventofcode.common.combinations

class XmasDecoder(val input: List<Long>, val windowSize: Int) {

    fun findFirstWronglyEncodedNumber(): Long = input[findIndexOfFirstWronglyEncodedNumber()]

    private fun findIndexOfFirstWronglyEncodedNumber(): Int =
            indexRange()
                    .find { index ->
                        0 == pairsOfDifferentNumbers(windowStartingAt(index)).count { it.sum() == input[index] }
                    } ?: 0

    private fun indexRange() = (windowSize..input.count() - windowSize)

    private fun windowStartingAt(targetIndex: Int) = HashSet(input.subList(targetIndex - windowSize, targetIndex))

    private fun pairsOfDifferentNumbers(window: HashSet<Long>) =
            window.combinations(ofSize = 2).filter { it.first() != it.last() }

    fun findEncryptionWeakness(): Long {
        val wronglyEncodedNumber = findFirstWronglyEncodedNumber()

        var startIndex = 0
        var contiguousSet: List<Long>
        do {
            contiguousSet = findContiguousSetAddingUpTo(wronglyEncodedNumber, startingAt = startIndex)
            ++startIndex
        } while (contiguousSet.isEmpty())

        return contiguousSet.minOrNull()!! + contiguousSet.maxOrNull()!!
    }

    private fun findContiguousSetAddingUpTo(wronglyEncodedNumber: Long, startingAt: Int): List<Long> {
        var size = 1
        var contiguousSet: List<Long>
        do {
            contiguousSet = input.subList(startingAt, startingAt + size)
            val sum = contiguousSet.sum()
            if (sum == wronglyEncodedNumber) {
                return contiguousSet
            }
            ++size
        } while (sum < wronglyEncodedNumber && startingAt + size < input.count())
        return emptyList()
    }
}
