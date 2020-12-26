package org.plaehn.adventofcode.day24

import org.plaehn.adventofcode.common.Grid
import org.plaehn.adventofcode.common.Vector
import org.plaehn.adventofcode.day24.Color.BLACK
import org.plaehn.adventofcode.day24.Color.WHITE
import org.plaehn.adventofcode.day24.Direction.*

class LobbyLayout(private val instructions: List<List<Direction>>) {

    private var tileGrid: Grid<Color> = Grid(mutableMapOf())

    fun applyInstructions(): Int =
        instructions
            .forEach { instruction -> visitTile(instruction) }
            .let { return countBlackTiles() }

    private fun countBlackTiles() = tileGrid.values().count { it == BLACK }

    private fun visitTile(directions: List<Direction>) {
        var position = Vector(0, 0)
        directions.forEach { direction -> position += direction.offset }
        tileGrid[position] = when (tileGrid[position]) {
            null, WHITE -> BLACK
            BLACK -> WHITE
        }
    }

    companion object {
        fun fromString(input: List<String>): LobbyLayout = LobbyLayout(input.map { parseInstruction(it) })

        private fun parseInstruction(input: String): List<Direction> {
            var lastChar: Char? = null
            return sequence {
                input.forEach { ch ->
                    when (ch) {
                        'e' ->
                            if (lastChar == null) {
                                yield(EAST)
                            } else {
                                when (lastChar.toString() + ch) {
                                    "se" -> yield(SOUTHEAST)
                                    "ne" -> yield(NORTHEAST)
                                    else -> throw IllegalArgumentException()
                                }
                                lastChar = null
                            }
                        'w' ->
                            if (lastChar == null) {
                                yield(WEST)
                            } else {
                                when (lastChar.toString() + ch) {
                                    "sw" -> yield(SOUTHWEST)
                                    "nw" -> yield(NORTHWEST)
                                    else -> throw IllegalArgumentException()
                                }
                                lastChar = null
                            }
                        else -> lastChar = ch
                    }
                }
            }.toList()
        }
    }
}

enum class Color {
    BLACK, WHITE
}

// Using cube coordinates, cf. https://www.redblobgames.com/grids/hexagons/#coordinates-cube
enum class Direction(val offset: Vector) {
    EAST(Vector(+1, -1, 0)),
    SOUTHEAST(Vector(0, -1, +1)),
    SOUTHWEST(Vector(-1, 0, +1)),
    WEST(Vector(-1, +1, 0)),
    NORTHWEST(Vector(0, +1, -1)),
    NORTHEAST(Vector(+1, 0, -1))
}