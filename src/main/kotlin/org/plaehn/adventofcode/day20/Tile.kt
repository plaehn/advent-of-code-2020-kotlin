package org.plaehn.adventofcode.day20

data class Tile(val id: Long, val rows: List<String>) {

    fun computeAllBorders(): List<String> {
        val borders = Direction.values().map { borderAt(it) }.toMutableList()
        borders.addAll(borders.map { it.reversed() })
        return borders
    }

    fun borderAt(direction: Direction) = when (direction) {
        Direction.NORTH -> rows.first()
        Direction.SOUTH -> rows.last()
        Direction.WEST -> columnAt(0)
        Direction.EAST -> columnAt(rows.count() - 1)
    }

    fun variants(): Set<Tile> = sequence {
        var tile = this@Tile
        repeat(4) {
            yield(tile)
            tile = tile.rotateRight()
        }
        tile = flipHorizontally()
        repeat(4) {
            yield(tile)
            tile = tile.rotateRight()
        }
        tile = flipVertically()
        repeat(4) {
            yield(tile)
            tile = tile.rotateRight()
        }
    }.toSet()

    internal fun flipHorizontally() = Tile(id, rows.reversed())

    internal fun flipVertically() = Tile(id, rows.map { it.reversed() })

    internal fun rotateRight() = Tile(id, (0 until rows.count()).map() { index -> columnAt(index).reversed() })

    private fun columnAt(index: Int) = rows.map { it[index] }.joinToString("")

    override fun toString() = "Tile $id:\n${rows.joinToString("\n")}"

    fun countWavesReplacedBySeaMonster(): Int = sequence {
        (0 until width() - seaMonster.width()).forEach { x ->
            (0 until height() - seaMonster.height()).forEach { y ->
                if (matchesOtherTileAt(x, y, seaMonster)) {
                    yield(countWavesReplacedBySeaMonster(x, y, seaMonster))
                }
            }
        }
    }.sum()

    private fun matchesOtherTileAt(x: Int, y: Int, tile: Tile): Boolean {
        (0 until tile.width()).forEach { xOffset ->
            (0 until tile.height()).forEach { yOffset ->
                val ownChar = rows[y + yOffset][x + xOffset]
                val otherChar = tile.rows[yOffset][xOffset]
                if (otherChar == '#' && ownChar != '#') {
                    return false
                }
            }
        }
        return true
    }

    private fun countWavesReplacedBySeaMonster(x: Int, y: Int, tile: Tile): Int =
        sequence {
            (0 until tile.width()).forEach { xOffset ->
                (0 until tile.height()).forEach { yOffset ->
                    val ownChar = rows[y + yOffset][x + xOffset]
                    val otherChar = tile.rows[yOffset][xOffset]
                    if (otherChar == '#' && ownChar == '#') {
                        yield(1)
                    }
                }
            }
        }.sum()

    private fun width(): Int = rows.first().count()

    private fun height(): Int = rows.count()

    fun countWaves(): Int = rows.joinToString("").count { it == '#' }

    companion object {

        val seaMonster = Tile(
            666, listOf(
                "                  # ",
                "#    ##    ##    ###",
                " #  #  #  #  #  #   "
            )
        )

        fun fromString(input: String): Tile {
            val lines = input.lines()
            val id = lines.first().split(' ', ':')[1].toLong()
            val rows = lines.drop(1).filter { it.isNotBlank() }
            return Tile(id, rows)
        }
    }
}