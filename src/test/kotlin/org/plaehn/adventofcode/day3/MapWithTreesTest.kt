package org.plaehn.adventofcode.day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.product
import org.plaehn.adventofcode.day3.MapWithTrees.TerrainType.*

class MapWithTreesTest {

    private val mapWithTrees = MapWithTrees.fromLines(listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
    ))

    @Test
    fun `Terrain at position within input map is correct`() {
        assertThat(mapWithTrees.terrainAt(Position(x = 0, y = 0))).isEqualTo(OPEN)
        assertThat(mapWithTrees.terrainAt(Position(x = 0, y = 1))).isEqualTo(TREE)
        assertThat(mapWithTrees.terrainAt(Position(x = 10, y = 0))).isEqualTo(OPEN)
        assertThat(mapWithTrees.terrainAt(Position(x = 10, y = 10))).isEqualTo(TREE)
    }

    @Test
    fun `Terrain below input rows is END_OF_MAP`() {
        assertThat(mapWithTrees.terrainAt(Position(x = 0, y = 11))).isEqualTo(END_OF_MAP)
        assertThat(mapWithTrees.terrainAt(Position(x = 2, y = 11))).isEqualTo(END_OF_MAP)
    }

    @Test
    fun `Terrain right of input rows is projected correctly`() {
        assertThat(mapWithTrees.terrainAt(Position(x = 11, y = 0))).isEqualTo(OPEN)
        assertThat(mapWithTrees.terrainAt(Position(x = 12, y = 0))).isEqualTo(OPEN)
        assertThat(mapWithTrees.terrainAt(Position(x = 13, y = 0))).isEqualTo(TREE)
        assertThat(mapWithTrees.terrainAt(Position(x = 14, y = 0))).isEqualTo(TREE)
        assertThat(mapWithTrees.terrainAt(Position(x = 15, y = 0))).isEqualTo(OPEN)
    }

    @Test
    fun `Compute path works correctly`() {
        val path = mapWithTrees.computePath(Slope(right = 3, down = 1))

        assertThat(path).containsExactly(OPEN, TREE, OPEN, TREE, TREE, OPEN, TREE, TREE, TREE, TREE)

        val numberOfTrees = mapWithTrees.computePath(Slope(right = 3, down = 1)).count { it == TREE }
        assertThat(numberOfTrees).isEqualTo(7)
    }

    @Test
    fun `Count number of trees on three right, one down slope`() {
        val map = MapWithTrees.fromLines(readInput())
        val numberOfTrees = map.computePath(Slope(right = 3, down = 1)).count { it == TREE }

        assertThat(numberOfTrees).isEqualTo(234)
    }

    @Test
    fun `Try different slopes and multiply tree counts`() {
        val mapWithTrees = MapWithTrees.fromLines(readInput())
        val slopes = listOf(
                Slope(right = 1, down = 1),
                Slope(right = 3, down = 1),
                Slope(right = 5, down = 1),
                Slope(right = 7, down = 1),
                Slope(right = 1, down = 2),
        )

        val paths = slopes.map { mapWithTrees.computePath(it) }
        val treeCounts = paths.map { path -> path.count { it == TREE }.toLong() }

        assertThat(treeCounts.product()).isEqualTo(5813773056)
    }

    private fun readInput(): List<String> =
            MapWithTrees::class.java
                    .getResource("input.txt")
                    .readText()
                    .lines()
                    .filter { it.isNotBlank() }
}
