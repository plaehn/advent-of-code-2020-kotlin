package org.plaehn.adventofcode.day20

import org.plaehn.adventofcode.common.Vector

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun opposite(): Direction = when (this) {
        NORTH -> SOUTH
        SOUTH -> NORTH
        WEST -> EAST
        EAST -> WEST
    }

    fun offset(): Vector = when (this) {
        NORTH -> Vector(0, -1)
        SOUTH -> Vector(0, 1)
        WEST -> Vector(-1, 0)
        EAST -> Vector(1, 0)
    }
}