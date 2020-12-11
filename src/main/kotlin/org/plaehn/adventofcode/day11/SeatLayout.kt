package org.plaehn.adventofcode.day11

import org.plaehn.adventofcode.day11.SeatLayout.CellType.OCCUPIED_SEAT

data class SeatLayout(internal val rows: List<List<CellType>>) {

    enum class CellType(val charRepresentation: Char) {
        FLOOR('.'),
        EMPTY_SEAT('L'),
        OCCUPIED_SEAT('#');

        companion object {
            fun fromCharRepresentation(charRepresentation: Char): CellType {
                return values().find { it.charRepresentation == charRepresentation }
                        ?: throw IllegalArgumentException("Unknown char representation $charRepresentation")
            }
        }
    }

    fun adjacentCells(rowIndex: Int, colIndex: Int): List<CellType> = sequence {
        (-1..1).map { rowOffset ->
            (-1..1).map { colOffset ->
                if (rowOffset != 0 || colOffset != 0) {
                    val adjacentRowIndex = rowIndex + rowOffset
                    val adjacentColIndex = colIndex + colOffset
                    if (isValidPosition(adjacentRowIndex, adjacentColIndex)) {
                        val adjacentCell = rows[rowIndex + rowOffset][colIndex + colOffset]
                        yield(adjacentCell)
                    }
                }
            }
        }
    }.toList()

    private fun isValidPosition(rowIndex: Int, colIndex: Int): Boolean =
            (0 until rows.count()).contains(rowIndex) && (0 until rows[0].count()).contains(colIndex)

    fun countOccupiedSeats(): Int = rows.flatten().count { it == OCCUPIED_SEAT }

    override fun toString(): String = rows
            .joinToString(separator = "\n") { row ->
                row.map { cellType ->
                    cellType.charRepresentation
                }.joinToString(separator = "")
            }

    companion object {
        fun fromRowStrings(rows: List<String>) = SeatLayout(rows.map { toRow(it) })

        private fun toRow(row: String) = row.map { CellType.fromCharRepresentation(it) }
    }
}
