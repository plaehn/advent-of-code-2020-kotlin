package org.plaehn.adventofcode.day20

import org.plaehn.adventofcode.common.groupByBlankLines
import org.plaehn.adventofcode.common.product

class JurassicJigsawSolver(private val tiles: List<Tile>) {

    fun computeProductOfCornerTileIds(): Long {
        val allBorders = tiles.map { it.computeAllBorders() }.flatten()
        val bordersWithCount = allBorders.groupingBy { it }.eachCount()
        val uniqueBorders = bordersWithCount.filter { it.value == 1 }
        val corners = tiles.filter { tile ->
            4 == tile.computeAllBorders().count { uniqueBorders.contains(it) }
        }
        return corners.map { it.id }.product()
    }

    override fun toString() = tiles.joinToString("\n\n")

    companion object {
        fun fromString(input: String): JurassicJigsawSolver =
            JurassicJigsawSolver(input.groupByBlankLines().map { Tile.fromString(it) })
    }
}

data class Tile(val id: Long, val rows: List<String>) {

    fun computeAllBorders(): List<String> {
        val borders = mutableListOf(
            rows.first(),
            rows.last(),
            rows.map { it.first() }.joinToString(""),
            rows.map { it.last() }.joinToString("")
        )
        borders.addAll(borders.map { it.reversed() })
        return borders
    }

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
