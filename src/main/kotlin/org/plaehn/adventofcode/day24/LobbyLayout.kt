package org.plaehn.adventofcode.day24

import org.plaehn.adventofcode.common.Grid
import org.plaehn.adventofcode.common.Vector
import org.plaehn.adventofcode.day24.Color.BLACK
import org.plaehn.adventofcode.day24.Color.WHITE
import org.plaehn.adventofcode.day24.Direction.*

class LobbyLayout(private val instructions: List<List<Direction>>) {

    private var tileGrid: Grid<Color> = Grid(mutableMapOf())

    fun livingArt(numberOfCycles: Int): Int {
        applyInstructions()
        return (1..numberOfCycles)
            .fold(tileGrid) { currentGrid, _ -> computeNextGrid(currentGrid) }
            .values()
            .count { it == BLACK }
    }

    private fun computeNextGrid(grid: Grid<Color>) = Grid(
        grid.enumerateCubesSpannedBy(grid.min() - 1, grid.max() + 1)
            .filter { isCubeCoordinate(it) }
            .map { position -> position to computeNewColor(position, grid) }
            .toMap().toMutableMap()
    )

    private fun isCubeCoordinate(position: Vector) = 0 == position.values.sum()

    private fun computeNewColor(position: Vector, grid: Grid<Color>): Color {
        val blackNeighbors = position
            .neighbors()
            .filter { isCubeCoordinate(it) }
            .count { grid[it] == BLACK }
        return if (grid[position] == BLACK) {
            if (blackNeighbors == 0 || blackNeighbors > 2) WHITE else BLACK
        } else {
            if (blackNeighbors == 2) BLACK else WHITE
        }
    }

    fun applyInstructions(): Int =
        instructions
            .forEach { instruction -> visitTile(instruction) }
            .let { return tileGrid.values().count { it == BLACK } }

    private fun visitTile(directions: List<Direction>) {
        var position = Vector(0, 0, 0)
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