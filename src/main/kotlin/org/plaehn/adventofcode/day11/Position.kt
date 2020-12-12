package org.plaehn.adventofcode.day11

data class Position(val x: Int, val y: Int) {

    operator fun plus(other: Position): Position = Position(x + other.x, y + other.y)

    fun isCenter(): Boolean = x == 0 && y == 0
}