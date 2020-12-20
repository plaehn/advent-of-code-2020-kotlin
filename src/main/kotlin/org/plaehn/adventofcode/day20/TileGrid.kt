package org.plaehn.adventofcode.day20

import org.plaehn.adventofcode.common.Vector

class TileGrid(private val edgeLength: Int, private val uniqueBorders: Set<String>) {

    var grid: MutableList<MutableList<Tile?>> = MutableList(edgeLength) { MutableList(edgeLength) { null } }

    fun get(position: Vector): Tile? = grid[position.x][position.y]

    fun set(position: Vector, tile: Tile) {
        grid[position.x][position.y] = tile
    }

    fun unset(position: Vector) {
        grid[position.x][position.y] = null
    }

    fun fitsAt(position: Vector, tile: Tile): Boolean =
        Direction.values().all { direction ->
            matchesNeighbor(tile, position, direction)
        }

    private fun matchesNeighbor(
        tile: Tile,
        position: Vector,
        direction: Direction
    ): Boolean {
        val border = tile.borderAt(direction)

        val neighborPosition = position + direction.offset()
        if (!isWithinBounds(neighborPosition)) {
            if (!isUnique(border)) {
                return false
            }
        } else {
            val neighbor = get(neighborPosition)
            if (neighbor != null) {
                val adjacentBorder = neighbor.borderAt(direction.opposite())
                if (adjacentBorder != border) {
                    return false
                }
            }
        }
        return true
    }

    private fun isWithinBounds(position: Vector): Boolean =
        (0 until edgeLength).contains(position.x) && (0 until edgeLength).contains(position.y)

    private fun isUnique(border: String): Boolean = uniqueBorders.contains(border)

    fun getCornerIds() = listOf(
        getTileIdAt(Vector(0, 0)),
        getTileIdAt(Vector(0, edgeLength - 1)),
        getTileIdAt(Vector(edgeLength - 1, 0)),
        getTileIdAt(Vector(edgeLength - 1, edgeLength - 1))
    )

    private fun getTileIdAt(vector: Vector) = get(vector)?.id ?: 0
}