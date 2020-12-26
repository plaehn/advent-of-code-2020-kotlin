package org.plaehn.adventofcode.day17

import org.plaehn.adventofcode.common.Grid
import org.plaehn.adventofcode.common.Vector

class ElvesEnergySource(private val initialGrid: Grid<Boolean>) {

    fun countActiveCubesAfterBootProcess(numberOfCycles: Int): Int =
        (1..numberOfCycles)
            .fold(initialGrid) { currentGrid, _ -> computeNextGrid(currentGrid) }
            .values()
            .count { it }

    private fun computeNextGrid(grid: Grid<Boolean>) = Grid(
        grid.enumerateCubesSpannedBy(grid.min() - 1, grid.max() + 1)
            .map { position -> position to computeNewIsActive(position, grid) }
            .toMap().toMutableMap()
    )

    private fun computeNewIsActive(position: Vector, grid: Grid<Boolean>): Boolean {
        val numberOfActiveNeighbors = position.neighbors().count { isActive(grid, it) }
        return if (isActive(grid, position)) {
            (2..3).contains(numberOfActiveNeighbors)
        } else {
            numberOfActiveNeighbors == 3
        }
    }

    private fun isActive(grid: Grid<Boolean>, position: Vector) = grid[position] ?: false
}