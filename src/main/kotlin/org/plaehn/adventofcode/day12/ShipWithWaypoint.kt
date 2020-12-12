package org.plaehn.adventofcode.day12

import org.plaehn.adventofcode.common.Position
import org.plaehn.adventofcode.common.withSign
import org.plaehn.adventofcode.day12.NavigationInstruction.Action.*
import kotlin.math.sign

class ShipWithWaypoint {

    var ship = Position(0, 0)
    var waypoint = Position(10, -1)

    fun apply(instruction: NavigationInstruction) {
        when (instruction.action) {
            NORTH -> waypoint += Position(0, -instruction.value)
            SOUTH -> waypoint += Position(0, instruction.value)
            WEST -> waypoint += Position(-instruction.value, 0)
            EAST -> waypoint += Position(instruction.value, 0)
            LEFT -> rotateWaypointCounterClockwise(instruction.value)
            RIGHT -> rotateWaypointClockwise(instruction.value)
            FORWARD -> ship += waypoint * instruction.value
        }
    }

    private fun rotateWaypointCounterClockwise(value: Int) {
        rotateWaypointClockwise(360 - value)
    }

    private fun rotateWaypointClockwise(value: Int) {
        var quadrant = Position(x = waypoint.x.sign, y = waypoint.y.sign)
        repeat(value / 90) {
            val newQuadrant = when (quadrant) {
                Position(1, 1) -> Position(-1, 1)
                Position(-1, 1) -> Position(-1, -1)
                Position(-1, -1) -> Position(1, -1)
                Position(1, -1) -> Position(1, 1)
                else -> throw IllegalStateException()
            }
            waypoint = Position(x = waypoint.y.withSign(newQuadrant.x), y = waypoint.x.withSign(newQuadrant.y))
            quadrant = newQuadrant
        }
    }

    fun computeManhattanDistance(): Int = kotlin.math.abs(ship.x) + kotlin.math.abs(ship.y)
}