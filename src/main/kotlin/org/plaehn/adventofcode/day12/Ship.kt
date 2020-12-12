package org.plaehn.adventofcode.day12

import org.plaehn.adventofcode.day12.NavigationInstruction.Action.*

class Ship {

    var x = 0
    var y = 0
    var direction: Int = 90

    fun apply(instruction: NavigationInstruction) {
        var action = instruction.action
        if (action == FORWARD) {
            action = when (direction) {
                0 -> NORTH
                90 -> EAST
                180 -> SOUTH
                270 -> WEST
                else -> throw IllegalStateException()
            }
        }
        when (action) {
            NORTH -> y -= instruction.value
            SOUTH -> y += instruction.value
            WEST -> x -= instruction.value
            EAST -> x += instruction.value
            LEFT -> changeDirection(-instruction.value)
            RIGHT -> changeDirection(instruction.value)
            else -> throw java.lang.IllegalStateException()
        }
    }

    private fun changeDirection(offset: Int) {
        direction += offset
        direction %= 360
        if (direction < 0) direction += 360
    }

    fun computeManhattanDistance(): Int = kotlin.math.abs(x) + kotlin.math.abs(y)
}