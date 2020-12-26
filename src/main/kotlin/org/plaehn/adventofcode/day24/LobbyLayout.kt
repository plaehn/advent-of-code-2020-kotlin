package org.plaehn.adventofcode.day24

import org.plaehn.adventofcode.common.Grid
import org.plaehn.adventofcode.common.Vector
import org.plaehn.adventofcode.day24.Color.BLACK
import org.plaehn.adventofcode.day24.Color.WHITE
import org.plaehn.adventofcode.day24.Direction.*

class LobbyLayout(private val instructions: List<List<Direction>>) {

    fun livingArt(numberOfCycles: Int): Int =
        (1..numberOfCycles)
            .fold(applyInstructions()) { currentGrid, _ -> computeNextGrid(currentGrid) }
            .values()
            .count { it == BLACK }

    private fun computeNextGrid(grid: Grid<Color>) = Grid(
        grid.enumerateCubesSpannedBy(grid.min() - 1, grid.max() + 1)
            .filter { isCubeCoordinate(it) }
            .map { position -> position to computeNewColor(position, grid) }
            .toMap()
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

    fun applyInstructionsAndCountBlackTiles(): Int =
        applyInstructions()
            .values()
            .count { it == BLACK }

    private fun applyInstructions() =
        instructions
            .fold(Grid<Color>(mapOf())) { currentGrid, instruction -> visitTile(currentGrid, instruction) }

    private fun visitTile(grid: Grid<Color>, directions: List<Direction>): Grid<Color> {
        val position = directions.fold(Vector(0, 0, 0)) { position, direction -> position + direction.offset }
        return grid.updated(
            position, when (grid[position]) {
                null, WHITE -> BLACK
                BLACK -> WHITE
            }
        )
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