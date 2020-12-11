package org.plaehn.adventofcode.day11

data class SeatLayout(val grid: Array<Array<CellType>>) {

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SeatLayout

        if (!grid.contentDeepEquals(other.grid)) return false

        return true
    }

    override fun hashCode(): Int {
        return grid.contentDeepHashCode()
    }

    override fun toString(): String = grid
            .joinToString(separator = "\n") { row ->
                row.map { cellType ->
                    cellType.charRepresentation
                }.joinToString(separator = "")
            }

    companion object {
        fun fromRowStrings(rows: List<String>) = SeatLayout(rows.map { toRow(it) }.toTypedArray())

        private fun toRow(row: String) = row.map { CellType.fromCharRepresentation(it) }.toTypedArray()
    }
}
