package org.plaehn.adventofcode.day20

import org.plaehn.adventofcode.common.groupByBlankLines

class JurassicJigsawSolver(private val tiles: List<Tile>) {

    override fun toString() = tiles.joinToString("\n\n")

    companion object {
        fun fromString(input: String): JurassicJigsawSolver =
            JurassicJigsawSolver(input.groupByBlankLines().map { Tile.fromString(it) })
    }
}

data class Tile(val id: Int, val rows: List<String>) {

    override fun toString() = "Tile $id:\n${rows.joinToString("\n")}"

    companion object {
        fun fromString(input: String): Tile {
            val lines = input.lines()
            val id = lines.first().split(' ', ':')[1].toInt()
            return Tile(id, lines.drop(1))
        }
    }
}
