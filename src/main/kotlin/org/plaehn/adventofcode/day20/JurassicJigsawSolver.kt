package org.plaehn.adventofcode.day20

import org.plaehn.adventofcode.common.Vector
import org.plaehn.adventofcode.common.groupByBlankLines
import org.plaehn.adventofcode.common.product
import kotlin.math.sqrt

class JurassicJigsawSolver(private val tiles: List<Tile>) {

    fun computeProductOfCornerTileIds(): Long = computeImage().getCornerIds().product()

    fun computeImage(): TileGrid {
        val grid = TileGrid(computeEdgeLength(), computeUniqueBorders().keys.toSet())
        assert(canSolve(grid, Vector(0, 0), HashSet(tiles)))
        return grid
    }

    private fun canSolve(grid: TileGrid, position: Vector, remainingTiles: Set<Tile>): Boolean {
        if (remainingTiles.isEmpty()) {
            return true
        }
        remainingTiles.forEach { tile ->
            tile.variants().forEach { variant ->
                if (grid.fitsAt(position, variant)) {
                    grid.set(position, variant)
                    if (canSolve(grid, nextPosition(position), remainingTiles.minus(tile))) {
                        return true
                    }
                    grid.unset(position)
                }
            }
        }
        return false
    }

    private fun nextPosition(position: Vector): Vector {
        var newY = position.y
        var newX = position.x + 1
        if (newX == computeEdgeLength()) {
            newX = 0
            ++newY
        }
        return Vector(newX, newY)
    }

    private fun computeEdgeLength() = sqrt(tiles.count().toDouble()).toInt()

    private fun computeUniqueBorders(): Map<String, Int> {
        val allBorders = tiles.map { it.computeAllBorders() }.flatten()
        val bordersWithCount = allBorders.groupingBy { it }.eachCount()
        return bordersWithCount.filter { it.value == 1 }
    }

    override fun toString() = tiles.joinToString("\n\n")

    companion object {
        fun fromString(input: String): JurassicJigsawSolver =
            JurassicJigsawSolver(input.groupByBlankLines().map { Tile.fromString(it) })
    }
}
