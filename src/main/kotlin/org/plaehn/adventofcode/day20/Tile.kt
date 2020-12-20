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

    companion object {
        fun fromString(input: String): Tile {
            val lines = input.lines()
            val id = lines.first().split(' ', ':')[1].toLong()
            val rows = lines.drop(1).filter { it.isNotBlank() }
            return Tile(id, rows)
        }
    }
}