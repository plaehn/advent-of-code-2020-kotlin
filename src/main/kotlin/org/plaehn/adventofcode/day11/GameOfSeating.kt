package org.plaehn.adventofcode.day11

import org.plaehn.adventofcode.day11.SeatLayout.CellType.EMPTY_SEAT
import org.plaehn.adventofcode.day11.SeatLayout.CellType.OCCUPIED_SEAT

class GameOfSeating(private val initialSeatLayout: SeatLayout) {

    fun computeStableSeatLayout(): SeatLayout {
        var currentSeatLayout = initialSeatLayout
        while (true) {
            val nextSeatLayout = computeNextSeatLayout(currentSeatLayout)
            if (nextSeatLayout == currentSeatLayout) {
                break
            }
            currentSeatLayout = nextSeatLayout
        }
        return currentSeatLayout
    }

    private fun computeNextSeatLayout(seatLayout: SeatLayout): SeatLayout = SeatLayout(
            seatLayout.rows
                    .mapIndexed { rowIndex, row ->
                        row.mapIndexed { colIndex, cellType ->
                            computeNewCellType(cellType, seatLayout.adjacentCells(rowIndex, colIndex))
                        }
                    }
    )

    private fun computeNewCellType(cellType: SeatLayout.CellType, adjacentCells: List<SeatLayout.CellType>) =
            when {
                cellType == EMPTY_SEAT && 0 == adjacentCells.count { it == OCCUPIED_SEAT } -> OCCUPIED_SEAT
                cellType == OCCUPIED_SEAT && 4 <= adjacentCells.count { it == OCCUPIED_SEAT } -> EMPTY_SEAT
                else -> cellType
            }
}
