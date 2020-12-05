package org.plaehn.adventofcode.day5

import org.plaehn.adventofcode.common.lowerHalf
import org.plaehn.adventofcode.common.upperHalf

class SeatIdComputer {

    fun computeSeatId(binarySpacePartition: String): Int {
        var rowRange = 0..127
        for (rowChar in binarySpacePartition.dropLast(3)) {
            rowRange = if (rowChar == 'F') {
                rowRange.lowerHalf()
            } else {
                rowRange.upperHalf()
            }
        }
        val row = rowRange.first

        var colRange = 0..7
        for (colChar in binarySpacePartition.drop(7)) {
            colRange = if (colChar == 'L') {
                colRange.lowerHalf()
            } else {
                colRange.upperHalf()
            }
        }
        val col = colRange.first

        return row * 8 + col
    }
}

