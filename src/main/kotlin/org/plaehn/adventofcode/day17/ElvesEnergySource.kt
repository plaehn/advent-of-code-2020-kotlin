package org.plaehn.adventofcode.day17

import org.plaehn.adventofcode.common.Position

class ElvesEnergySource(private val initialGrid: Grid3D) {

    fun countActiveCubesAfterBootProcess(numberOfCycles: Int): Int =
        (1..numberOfCycles)
            .fold(initialGrid) { currentGrid, _ -> computeNextGrid(currentGrid) }
            .numberOfActiveCubes()

    private fun computeNextGrid(grid: Grid3D) = Grid3D(
        grid.enumerateCubesSpannedBy(grid.min() - Position(1, 1, 1), grid.max() + Position(1, 1, 1))
            .map { position -> position to computeNewIsActive(position, grid) }
            .toMap()
    )

    private fun computeNewIsActive(position: Position, grid: Grid3D): Boolean {
        val numberOfActiveNeighbors = position.neighbors().count { grid.isActive(it) }
        return if (grid.isActive(position)) {
            (2..3).contains(numberOfActiveNeighbors)
        } else {
            numberOfActiveNeighbors == 3
        }
    }
}

data class Grid3D(val cubes: Map<Position, Boolean>) {

    fun min(): Position = Position(cubes.keys.minOf { it.x }, cubes.keys.minOf { it.y }, cubes.keys.minOf { it.z })

    fun max(): Position = Position(cubes.keys.maxOf { it.x }, cubes.keys.maxOf { it.y }, cubes.keys.maxOf { it.z })

    fun isActive(position: Position): Boolean = cubes[position] ?: false

    fun numberOfActiveCubes(): Int = cubes.values.count { it }

    fun enumerateCubesSpannedBy(min: Position, max: Position): List<Position> = sequence {
        IntRange(min.x, max.x).forEach { x ->
            IntRange(min.y, max.y).forEach { y ->
                IntRange(min.z, max.z).forEach { z ->
                    yield(Position(x, y, z))
                }
            }
        }
    }.toList()

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
