package org.plaehn.adventofcode.day17

import com.google.common.collect.Sets
import org.plaehn.adventofcode.common.Vector

class ElvesEnergySource(private val initialGrid: Grid) {

    fun countActiveCubesAfterBootProcess(numberOfCycles: Int): Int =
        (1..numberOfCycles)
            .fold(initialGrid) { currentGrid, _ -> computeNextGrid(currentGrid) }
            .numberOfActiveCubes()

    private fun computeNextGrid(grid: Grid) = Grid(
        grid.enumerateCubesSpannedBy(grid.min() - 1, grid.max() + 1)
            .map { position -> position to computeNewIsActive(position, grid) }
            .toMap()
    )

    private fun computeNewIsActive(position: Vector, grid: Grid): Boolean {
        val numberOfActiveNeighbors = position.neighbors().count { grid.isActive(it) }
        return if (grid.isActive(position)) {
            (2..3).contains(numberOfActiveNeighbors)
        } else {
            numberOfActiveNeighbors == 3
        }
    }
}

data class Grid(val cubes: Map<Vector, Boolean>) {

    fun min(): Vector = Vector((0 until dimension())
                                   .map { index -> cubes.keys.minOf { vector -> vector[index] } }
                                   .toList())

    fun max(): Vector = Vector((0 until dimension())
                                   .map { index -> cubes.keys.maxOf { vector -> vector[index] } }
                                   .toList())

    private fun dimension(): Int = cubes.keys.first().dimension()

    fun isActive(position: Vector): Boolean = cubes[position] ?: false

    fun numberOfActiveCubes(): Int = cubes.values.count { it }

    fun enumerateCubesSpannedBy(min: Vector, max: Vector) =
        Sets
            .cartesianProduct((0 until dimension()).map { (min[it]..max[it]).toSet() })
            .map { Vector(it) }

    companion object {
        fun from2DInput(dimensions: Int, lines: List<String>): Grid = Grid(
            sequence {
                lines.forEachIndexed { x, line ->
                    line.forEachIndexed { y, state ->
                        val active = state == '#'
                        val vector = Vector(listOf(x, y) + List(dimensions - 2) { 0 })
                        yield(vector to active)
                    }
                }
            }.toMap()
        )
    }
}
