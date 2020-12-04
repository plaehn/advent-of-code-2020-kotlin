package org.plaehn.adventofcode.day3

import org.plaehn.adventofcode.day3.MapWithTrees.TerrainType.*

data class MapWithTrees(val rows: List<List<TerrainType>>) {

    enum class TerrainType {
        TREE, OPEN, END_OF_MAP
    }

    private val width = rows[0].count()

    fun terrainAt(position: Position): TerrainType {
        if (position.y >= rows.count()) {
            return END_OF_MAP
        }

        val projectedIndex = position.x % width
        return rows[position.y][projectedIndex]
    }

    fun computePath(slope: Slope): List<TerrainType> {
        var path = mutableListOf<TerrainType>()
        var position = Position(0, 0)
        do {
            position += slope
            path.add(terrainAt(position))
        } while (path.last() != END_OF_MAP)
        return path.dropLast(1)
    }

    companion object {
        fun fromLines(lines: List<String>): MapWithTrees = MapWithTrees(lines.map { constructRow(it) })

        private fun constructRow(line: String): List<TerrainType> = line.map { fromChar(it) }

        private fun fromChar(char: Char): TerrainType = when (char) {
            '#' -> TREE
            else -> OPEN
        }
    }
}

data class Slope(val right: Int, val down: Int)

data class Position(val x: Int, val y: Int) {
    operator fun plus(slope: Slope): Position = Position(this.x + slope.right, this.y + slope.down)
}

