package org.plaehn.adventofcode.common

data class Position(val x: Int, val y: Int, val z: Int = 0) {

    operator fun plus(other: Position): Position = Position(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Position): Position = Position(x - other.x, y - other.y, z - other.z)

    operator fun times(value: Int): Position = Position(x * value, y * value, z * value)

    fun neighbors(threeDim: Boolean = true): List<Position> =
        neighborOffsets(threeDim)
            .map { offset -> this + offset }
            .toList()

    fun neighborOffsets(threeDim: Boolean = true) = sequence<Position> {
        val zRange = if (threeDim) (-1..1) else (0..0)
        (-1..1).forEach { xOffset ->
            (-1..1).forEach { yOffset ->
                zRange.forEach { zOffset ->
                    val offset = Position(xOffset, yOffset, zOffset)
                    if (!offset.isCenter()) {
                        yield(offset)
                    }
                }
            }
        }
    }.toList()

    private fun isCenter(): Boolean = x == 0 && y == 0 && z == 0
}