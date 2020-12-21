package org.plaehn.adventofcode.day20

import org.plaehn.adventofcode.common.Vector

class TileGrid(private val edgeLength: Int, private val uniqueBorders: Set<String>) {

    var grid: MutableList<MutableList<Tile?>> = MutableList(edgeLength) { MutableList(edgeLength) { null } }

    fun get(position: Vector): Tile? = grid[position.y][position.x]

    fun set(position: Vector, tile: Tile) {
        grid[position.y][position.x] = tile
    }

    fun unset(position: Vector) {
        grid[position.y][position.x] = null
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

    fun getImage(): Tile {
        (0 until edgeLength).forEach { y ->
            (0 until edgeLength).forEach { x ->
                grid[y][x] = removeBorder(grid[y][x]!!)
            }
        }

        val tileHeight = grid[0][0]!!.rows.count()

        val imageRows = sequence {
            (0 until edgeLength).forEach { y ->
                (0 until tileHeight).forEach { tileRow ->
                    val imageRow = (0 until edgeLength).joinToString("") { x ->
                        grid[y][x]!!.rows[tileRow]
                    }
                    yield(imageRow)
                }
            }
        }.toList()

        return Tile(0, imageRows)
    }

    private fun removeBorder(tile: Tile): Tile =
        Tile(tile.id, tile.rows.subList(1, tile.rows.count() - 1).map { it.subSequence(1, it.count() - 1).toString() })

    override fun toString(): String {
        val builder = StringBuilder()
        (0 until edgeLength).forEach { y ->
            (0 until edgeLength).forEach { x ->
                builder.append("Tile (y=$y, x=$x):\n")
                builder.append(grid[y][x]!!.toString())
                builder.append("\n")
            }
        }
        return builder.toString()
    }
}