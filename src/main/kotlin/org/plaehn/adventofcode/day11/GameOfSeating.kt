package org.plaehn.adventofcode.day11

import org.plaehn.adventofcode.day11.SeatLayout.CellType
import org.plaehn.adventofcode.day11.SeatLayout.CellType.EMPTY_SEAT
import org.plaehn.adventofcode.day11.SeatLayout.CellType.OCCUPIED_SEAT

class GameOfSeating(
    private val initialSeatLayout: SeatLayout,
    private val considerOnlyDirectlyAdjacentSeats: Boolean = true,
    private val numberOfOccupiedSeatsToLeaveSeatThreshold: Int = 4
) {

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
                    val position = Position(x = colIndex, y = rowIndex)
                    val adjacentCells = seatLayout.adjacentSeats(position, considerOnlyDirectlyAdjacentSeats)
                    computeNewCellType(cellType, adjacentCells)
                }
            }
    )

    private fun computeNewCellType(cellType: CellType, adjacentCells: List<CellType>) =
        when {
            cellType == EMPTY_SEAT && 0 == adjacentCells.count { it == OCCUPIED_SEAT } -> OCCUPIED_SEAT
            cellType == OCCUPIED_SEAT && tooManyOccupiedAdjacentSeats(adjacentCells) -> EMPTY_SEAT
            else -> cellType
        }

    private fun tooManyOccupiedAdjacentSeats(adjacentCells: List<CellType>) =
        numberOfOccupiedSeatsToLeaveSeatThreshold <= adjacentCells.count { it == OCCUPIED_SEAT }
}
