package org.plaehn.adventofcode.day20

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TileTest {

    private val tile = Tile(
        1L, listOf(
            "..#",
            "##.",
            "#.."
        )
    )

    @Test
    fun `Compute all borders`() {
        assertThat(tile.computeAllBorders()).containsExactlyInAnyOrder(
            "..#", "#..",
            "#..", "..#",
            ".##", "##.",
            "#..", "..#"
        )
    }

    @Test
    fun `Flip horizontally`() {
        assertThat(tile.flipHorizontally().rows).isEqualTo(
            listOf(
                "#..",
                "##.",
                "..#",
            )
        )
    }

    @Test
    fun `Flip vertically`() {
        assertThat(tile.flipVertically().rows).isEqualTo(
            listOf(
                "#..",
                ".##",
                "..#"
            )
        )
    }

    @Test
    fun `Rotate right`() {
        assertThat(tile.rotateRight().rows).isEqualTo(
            listOf(
                "##.",
                ".#.",
                "..#"
            )
        )
    }

    @Test
    fun `Compute variants`() {
        assertThat(tile.variants()).hasSize(8)
    }
}