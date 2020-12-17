package org.plaehn.adventofcode.day12

import org.plaehn.adventofcode.common.Vector
import org.plaehn.adventofcode.common.withSign
import org.plaehn.adventofcode.day12.NavigationInstruction.Action.*
import kotlin.math.sign

class ShipWithWaypoint {

    var ship = Vector(0, 0)
    var waypoint = Vector(10, -1)

    fun apply(instruction: NavigationInstruction) {
        when (instruction.action) {
            NORTH -> waypoint += Vector(0, -instruction.value)
            SOUTH -> waypoint += Vector(0, instruction.value)
            WEST -> waypoint += Vector(-instruction.value, 0)
            EAST -> waypoint += Vector(instruction.value, 0)
            LEFT -> rotateWaypointCounterClockwise(instruction.value)
            RIGHT -> rotateWaypointClockwise(instruction.value)
            FORWARD -> ship += waypoint * instruction.value
        }
    }

    private fun rotateWaypointCounterClockwise(value: Int) {
        rotateWaypointClockwise(360 - value)
    }

    private fun rotateWaypointClockwise(value: Int) {
        var quadrant = Vector(waypoint.x.sign, waypoint.y.sign)
        repeat(value / 90) {
            val newQuadrant = when (quadrant) {
                Vector(1, 1) -> Vector(-1, 1)
                Vector(-1, 1) -> Vector(-1, -1)
                Vector(-1, -1) -> Vector(1, -1)
                Vector(1, -1) -> Vector(1, 1)
                else -> throw IllegalStateException()
            }
            waypoint = Vector(waypoint.y.withSign(newQuadrant.x), waypoint.x.withSign(newQuadrant.y))
            quadrant = newQuadrant
        }
    }

    fun computeManhattanDistance(): Int = kotlin.math.abs(ship.x) + kotlin.math.abs(ship.y)
}