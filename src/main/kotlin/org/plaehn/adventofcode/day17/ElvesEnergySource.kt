package org.plaehn.adventofcode.day17

import com.google.common.collect.Sets
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
            .toMap()
    )

    private fun computeNewIsActive(position: Vector, grid: Grid<Boolean>): Boolean {
        val numberOfActiveNeighbors = position.neighbors().count { isActive(grid, it) }
        return if (isActive(grid, position)) {
            (2..3).contains(numberOfActiveNeighbors)
        } else {
            numberOfActiveNeighbors == 3
        }
    }

    private fun isActive(grid: Grid<Boolean>, position: Vector) = grid.valueAt(position) ?: false
}

data class Grid<T>(val initialData: Map<Vector, T>) {

    fun min(): Vector = Vector((0 until dimensions())
                                   .map { index -> initialData.keys.minOf { vector -> vector[index] } }
                                   .toList())

    fun max(): Vector = Vector((0 until dimensions())
                                   .map { index -> initialData.keys.maxOf { vector -> vector[index] } }
                                   .toList())

    private fun dimensions(): Int = initialData.keys.first().dimension()

    fun valueAt(position: Vector): T? = initialData[position]

    fun values() = initialData.values

    fun enumerateCubesSpannedBy(min: Vector, max: Vector) =
        Sets
            .cartesianProduct((0 until dimensions()).map { (min[it]..max[it]).toSet() })
            .map { Vector(it) }

    companion object {
        fun <T> from2DInput(dimensions: Int, lines: List<String>, charToValue: (Char) -> T): Grid<T> = Grid(
            sequence {
                lines.forEachIndexed { x, line ->
                    line.forEachIndexed { y, state ->
                        val active = charToValue(state)
                        val vector = Vector(listOf(x, y) + List(dimensions - 2) { 0 })
                        yield(vector to active)
                    }
                }
            }.toMap()
        )
    }
}
