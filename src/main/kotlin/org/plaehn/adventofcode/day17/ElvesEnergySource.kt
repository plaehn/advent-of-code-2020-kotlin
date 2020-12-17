package org.plaehn.adventofcode.day17

import org.plaehn.adventofcode.common.Position

class ElvesEnergySource(private val initialGrid: Grid3D) {

    fun countActiveCubesAfterBootProcess(numberOfCycles: Int): Int {
        var grid = initialGrid
        repeat(numberOfCycles) {
            grid = computeNextGrid(grid)
        }
        return grid.cubes.values.count { it }
    }

    private fun computeNextGrid(grid: Grid3D): Grid3D {
        val min = grid.min() - Position(1, 1, 1)
        val max = grid.max() + Position(1, 1, 1)

        return Grid3D(sequence {
            IntRange(min.x, max.x).forEach { x ->
                IntRange(min.y, max.y).forEach { y ->
                    IntRange(min.z, max.z).forEach { z ->
                        val position = Position(x, y, z)
                        val numberOfActiveNeighbors = position.neighbors().count { grid.isActive(it) }
                        val newIsActive = if (grid.isActive(position)) {
                            (2..3).contains(numberOfActiveNeighbors)
                        } else {
                            numberOfActiveNeighbors == 3
                        }

                        yield(position to newIsActive)
                    }
                }
            }
        }.toMap())
    }
}

data class Grid3D(val cubes: Map<Position, Boolean>) {

    fun min(): Position = Position(cubes.keys.minOf { it.x }, cubes.keys.minOf { it.y }, cubes.keys.minOf { it.z })

    fun max(): Position = Position(cubes.keys.maxOf { it.x }, cubes.keys.maxOf { it.y }, cubes.keys.maxOf { it.z })

    fun isActive(position: Position): Boolean = cubes[position] ?: false

    companion object {
        fun from2DInput(lines: List<String>): Grid3D = Grid3D(
            sequence {
                lines.forEachIndexed { x, line ->
                    line.forEachIndexed { y, state ->
                        val active = state == '#'
                        yield(Position(x, y, 0) to active)
                    }
                }
            }.toMap()
        )
    }
}
