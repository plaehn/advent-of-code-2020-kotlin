package org.plaehn.adventofcode.day5

import org.plaehn.adventofcode.common.lowerHalf
import org.plaehn.adventofcode.common.upperHalf

class SeatIdComputer {

    fun computeSeatId(binarySpacePartition: String): Int {
        val row = foldRange(0..127, binarySpacePartition.dropLast(3), 'F')
        val col = foldRange(0..7, binarySpacePartition.drop(7), 'L')
        return row * 8 + col
    }

    private fun foldRange(inputRange: IntRange, binarySpacePartition: String, lowerHalfChar: Char): Int =
            binarySpacePartition.fold(inputRange) { range, char ->
                if (char == lowerHalfChar) {
                    range.lowerHalf()
                } else {
                    range.upperHalf()
                }
            }.first
}

