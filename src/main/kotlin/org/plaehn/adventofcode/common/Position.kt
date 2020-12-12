package org.plaehn.adventofcode.common

data class Position(val x: Int, val y: Int) {

    operator fun plus(other: Position): Position = Position(x + other.x, y + other.y)

    operator fun times(value: Int): Position = Position(x * value, y * value)
    
    fun isCenter(): Boolean = x == 0 && y == 0
}