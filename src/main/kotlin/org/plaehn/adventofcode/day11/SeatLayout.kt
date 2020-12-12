package org.plaehn.adventofcode.day11

import org.plaehn.adventofcode.day11.SeatLayout.CellType.FLOOR
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

    fun adjacentSeats(currentPosition: Position, considerOnlyDirectlyAdjacentSeats: Boolean): List<CellType> =
        sequence {
            (-1..1).map { rowOffset ->
                (-1..1).map { colOffset ->
                    val trajectory = Position(rowOffset, colOffset)
                    if (!trajectory.isCenter()) {
                        findSeatAlong(
                            trajectory, from = currentPosition, considerOnlyDirectlyAdjacentSeats
                        )?.let { yield(it) }
                    }
                }
            }
        }.toList()

    private fun findSeatAlong(
        trajectory: Position,
        from: Position,
        considerOnlyDirectlyAdjacentSeats: Boolean
    ): CellType? {
        var position = from
        do {
            position += trajectory
            if (!isValidPosition(position)) {
                break
            }
            val cellType = rows[position.y][position.x]
            if (cellType != FLOOR) {
                return cellType
            }
        } while (!considerOnlyDirectlyAdjacentSeats)
        return null
    }

    private fun isValidPosition(position: Position): Boolean =
        (0 until rows.count()).contains(position.y) && (0 until rows[0].count()).contains(position.x)

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